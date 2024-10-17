import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.juegopreguntasandroid.R

class ScoreFragment : Fragment() {
    private lateinit var viewModel: QuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_score, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(QuizViewModel::class.java)
        displayScore(view)

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        progressBar.max = viewModel.getTotalQuestions()
        progressBar.progress = viewModel.getCurrentQuestionIndex() + 1

        view.findViewById<Button>(R.id.btnRestartGame).setOnClickListener {
            viewModel.resetGame()  // Llama a resetGame() para reiniciar el juego
            progressBar.progress = 0  // Reinicia la barra de progreso
            (activity as FragmentActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, WelcomeFragment())
                .commit()
        }
    }

    private fun displayScore(view: View) {
        view.findViewById<TextView>(R.id.txtScore).text = "Tu puntuación final es: ${viewModel.getScore()}"
    }
}
