import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import com.example.juegopreguntasandroid.R

class AnswerFragment : Fragment() {
    private lateinit var viewModel: QuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_answer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(QuizViewModel::class.java)
        displayAnswerFeedback(view)

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        progressBar.max = viewModel.getTotalQuestions()
        progressBar.progress = viewModel.getCurrentQuestionIndex() + 1  // Inicializa el progreso de la barra de acuerdo con el ViewModel

        view.findViewById<Button>(R.id.btnNextQuestion).setOnClickListener {
            if (viewModel.hasMoreQuestions()) {
                viewModel.moveToNextQuestion()
                (activity as FragmentActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, QuestionFragment())
                    .addToBackStack(null)
                    .commit()
            } else {
                (activity as FragmentActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ScoreFragment())
                    .commit()
            }
        }
    }



    private fun displayAnswerFeedback(view: View) {
        if (viewModel.isAnswerCorrect(viewModel.getLastSelectedOption())) {
            view.findViewById<TextView>(R.id.txtFeedback).text = "Respuesta Correcta!"
        } else {
            view.findViewById<TextView>(R.id.txtFeedback).text = "Respuesta Incorrecta! La correcta era: ${viewModel.getCorrectAnswerExplanation()}"
        }
    }
}