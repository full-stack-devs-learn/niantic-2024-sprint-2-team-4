document.addEventListener("DOMContentLoaded", () => {
    const nextButton = document.getElementById("next-question-btn");

    if (nextButton) {
        nextButton.classList.add('hide'); // Hide button initially

        const quizId = nextButton.getAttribute("data-quiz-id");

        if (!quizId) {
            console.error("Quiz ID is missing.");
            return;
        }

        const currentQuestionIdElement = document.getElementById("current-question-id");
        const currentQuestionId = parseInt(currentQuestionIdElement.value, 10);

        if (isNaN(currentQuestionId)) {
            console.error("Invalid currentQuestionId:", currentQuestionIdElement.value);
            return;
        }

        nextButton.addEventListener("click", () => {
            console.log(quizId);
            location.href = `/quiz/${quizId}/${currentQuestionId}`;

    /*
            fetch(`/quiz/${quizId}/next/${currentQuestionId}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
                })
                .then(data => {
                    if (data) {
                        const [questionText, questionNumber, totalQuestions, nextQuestionId] = data.split('|');
                        document.querySelector('.question-container h2').textContent = questionText;
                        document.querySelector('.question-container p').textContent = `Question ${questionNumber} of ${totalQuestions}`;
                        currentQuestionIdElement.value = nextQuestionId;
                        nextButton.classList.remove('hide');
                    } else {
                        alert('No more questions available.');
                    }
                })
                .catch(error => {
                    console.error('Error fetching next question:', error);
                    alert('An error occurred while fetching the next question. Please try again later.');
                });
                */
        });
    } else {
        console.error("Next button not found");
    }
});
