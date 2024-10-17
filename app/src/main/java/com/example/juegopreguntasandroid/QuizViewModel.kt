import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    fun resetGame() {
        currentQuestionIndex = 0
        score = 0
        lastSelectedOption = -1
    }
    private val questions = listOf(
        Question("¿Cuál es el país más grande del mundo por área?", listOf("China", "Rusia", "Canadá", "Estados Unidos"), 1, "Rusia es el país más grande del mundo por área, cubriendo aproximadamente el 11% de la masa terrestre del planeta."),
        Question("¿Quién pintó la Mona Lisa?", listOf("Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Claude Monet"), 1, "Leonardo da Vinci pintó la Mona Lisa, una de las obras de arte más reconocidas en el mundo."),
        Question("¿Cuál es el océano más grande del mundo?", listOf("Atlántico", "Índico", "Pacífico", "Ártico"), 2, "El océano Pacífico es el más grande del mundo, cubriendo más de 63 millones de millas cuadradas."),
        Question("¿Quién escribió 'Don Quijote de la Mancha'?", listOf("Gabriel García Márquez", "Miguel de Cervantes", "Jorge Luis Borges", "Pablo Neruda"), 1, "Miguel de Cervantes escribió 'Don Quijote de la Mancha', una de las obras más influyentes de la literatura occidental."),
        Question("¿Cuál es el planeta más grande del sistema solar?", listOf("Tierra", "Marte", "Júpiter", "Saturno"), 2, "Júpiter es el planeta más grande del sistema solar, con un diámetro de aproximadamente 142,984 km.")
    )
    private var currentQuestionIndex = 0
    private var score = 0
    private var lastSelectedOption: Int = -1

    fun getCurrentQuestion(): Question = questions[currentQuestionIndex]
    fun getTotalQuestions(): Int = questions.size
    fun getCurrentQuestionIndex(): Int = currentQuestionIndex

    fun checkAnswer(selectedOption: Int) {
        lastSelectedOption = selectedOption
        if (selectedOption == questions[currentQuestionIndex].correctOptionIndex) {
            score++
        }
    }

    fun moveToNextQuestion() {
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
        }
    }

    fun hasMoreQuestions(): Boolean = currentQuestionIndex < questions.size - 1

    fun isAnswerCorrect(selectedOption: Int): Boolean = questions[currentQuestionIndex].correctOptionIndex == selectedOption

    fun getCorrectAnswerExplanation(): String = questions[currentQuestionIndex].explanation

    fun getLastSelectedOption(): Int = lastSelectedOption

    fun getScore(): Int = score
}

data class Question(
    val text: String,
    val options: List<String>,
    val correctOptionIndex: Int,
    val explanation: String
)
