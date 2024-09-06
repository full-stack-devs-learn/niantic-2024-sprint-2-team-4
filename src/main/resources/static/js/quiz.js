document.addEventListener("DOMContentLoaded", () => {
    const quizzes = document.getElementById("start-quiz-btn");
    const quizId = document.getAttribute("data-quiz-id");

    // start button redirects page to the first question in the quiz
    startButton.addEventListener("click", () => {
        window.location.href=`quiz/${quizId}/start`;
    })
});