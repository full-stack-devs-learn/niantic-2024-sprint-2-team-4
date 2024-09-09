document.addEventListener('DOMContentLoaded', function() {
    const answerOptionsForm = document.getElementById('answer-options');
    const nextButton = document.getElementById('next-question-btn');

    if (!nextButton) {
        console.error("Next button not found");
        return;
    }

    // Function to check if an option is selected
    function checkAnswerSelection() {
        const selectedAnswer = document.querySelector('input[name="answer"]:checked');
        if (selectedAnswer) {
            nextButton.classList.remove('hide'); // Show the button
        } else {
            nextButton.classList.add('hide'); // Hide the button
        }
    }

    // Initial check to set button visibility based on pre-selected options (if any)
    checkAnswerSelection();

    // Add event listener to radio buttons
    answerOptionsForm.addEventListener('change', checkAnswerSelection);

    // Handle next button click
    nextButton.addEventListener("click", () => {
        const quizId = nextButton.getAttribute("data-quiz-id");
        const currentQuestionIdElement = document.getElementById("current-question-id");
        const currentQuestionId = parseInt(currentQuestionIdElement.value, 10);
        const isLastQuestionElement = document.getElementById("is-last-question");
        const isLastQuestion = isLastQuestionElement && isLastQuestionElement.value === 'true'; // Checks if it's the last question

        if (isNaN(currentQuestionId)) {
            console.error("Invalid currentQuestionId:", currentQuestionIdElement.value);
            return;
        }

        if (isLastQuestion) {
            location.href = `/quiz/${quizId}/result`;
        } else {
            location.href = `/quiz/${quizId}/next/${currentQuestionId}`;
        }
    });
});

