document.addEventListener("DOMContentLoaded", () => {
    const nextButton = document.getElementById("next-question-btn");

    // Retrieve quizId from data attribute
    const quizId = nextButton.getAttribute("data-quiz-id");

    // Retrieve currentQuestionId from a hidden field or similar element
    const currentQuestionId = parseInt(document.getElementById("current-question-id").value);

    nextButton.addEventListener("click", () => {
        // Make a fetch request to get the next question
        fetch(`/quiz/${quizId}/next/${currentQuestionId}`)
            .then(response => response.text()) // Get response as plain text
            .then(data => {
                if (data) {
                    // Split the data by new lines
                    const [questionText, questionNumber, totalQuestions, nextQuestionId] = data.split('\n');

                    // Update the page with the new question and question number
                    document.querySelector('.question-container h2').textContent = questionText;
                    document.querySelector('.question-container p').textContent = `Question ${questionNumber} of ${totalQuestions}`;

                    // Update currentQuestionId to the next question's ID
                    document.getElementById("current-question-id").value = nextQuestionId; // Update hidden field
                } else {
                    // Handle the end of the quiz (e.g., display the final score)
                    alert('No more questions available.');
                }
            })
            .catch(error => {
                        console.error('Error fetching next question:', error);
                        alert('An error occurred while fetching the next question. Please try again later.');
            });
    });
});