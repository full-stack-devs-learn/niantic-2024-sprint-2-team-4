document.addEventListener("DOMContentLoaded", () => {
    const nextButton = document.getElementById("next-question-btn");
    const quizId = document.getAttribute("data-quiz-id");

    nextButton.addEventListener("click", () => {
    fetch(`/quiz/${quizId}/next`)
        .then(response => response.text())
        .then(data => {
            document.querySelector('question-container h2').textContent = data;
        })
        .catch(error => console.error('Error fetching next question: ', error));
    })
})