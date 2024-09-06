document.addEventListener("DOMContentLoaded", () => {
    const startButton = document.getElementById("start-quiz-btn");
    const quizId = document.getElementById("data-quiz-id");

    // start button redirects page to the first question in the quiz
    startButton.addEventListener("click", () => {
        window.location.href = `quiz/${quizId}/start`;
    });
});