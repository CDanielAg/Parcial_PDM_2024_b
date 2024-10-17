import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.juegopreguntasandroid.R

class QuestionFragment {
    private lateinit var viewModel: QuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(QuizViewModel::class.java)
        displayQuestion(view)

        view.findViewById<Button>(R.id.btnSubmitAnswer).setOnClickListener {
            val selectedOption = getSelectedOption(view)
            if (selectedOption != -1) {
                viewModel.checkAnswer(selectedOption)
                (activity as FragmentActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, AnswerFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun displayQuestion(view: View) {
        val question = viewModel.getCurrentQuestion()
        view.findViewById<TextView>(R.id.txtQuestion).text = question.text
        view.findViewById<RadioGroup>(R.id.radioGroupOptions)
        val radioButtons = listOf(view.findViewById(R.id.option1), view.findViewById(R.id.option2), view.findViewById(R.id.option3), view.findViewById<RadioButton>(R.id.option4))
        question.options.forEachIndexed { index, option ->
            radioButtons[index].text = option
        }
    }
}

private fun getSelectedOption(view: View): Int {
    // Devuelve el ID de la opción seleccionada o -1 si no hay nada seleccionado
    return when (view.findViewById<RadioGroup>(R.id.radioGroupOptions).checkedRadioButtonId) {
        R.id.option1 -> 0
        R.id.option2 -> 1
        R.id.option3 -> 2
        R.id.option4 -> 3
        else -> -1
    }
}
