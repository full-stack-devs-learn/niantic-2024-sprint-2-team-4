document.addEventListener("DOMContentLoaded", () => {
    const startButton = document.getElementById("start-quiz-btn");
    const quizId = startButton.value;

    startButton.addEventListener("click", () => {
        window.location.href = `/quiz/${quizId}/start`;
    });
});