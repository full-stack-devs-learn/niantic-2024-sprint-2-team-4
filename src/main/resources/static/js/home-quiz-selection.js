document.addEventListener("DOMContentLoaded", () => {
    const quizzes = document.querySelectorAll("card mb-2");
    quizzes.forEach(quiz => {
        quiz.addEventListener("click", () => {
            const quizId = quiz.getAttribute("data-id");
            window.location.href = `/quiz/${quizId}`;
        });
    });