let userAnswers = {};

document.addEventListener('DOMContentLoaded', function() {
    initPage();
});

function initPage() {
    const answerOptionsForm = document.getElementById('answer-options');
    const nextButton = document.getElementById('next-question-btn');
    const quizId = nextButton.getAttribute("data-quiz-id");

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

    checkAnswerSelection();

    answerOptionsForm.addEventListener('change', checkAnswerSelection);

    // Handle next button click
    nextButton.addEventListener("click", () => {
        const quizId = nextButton.getAttribute("data-quiz-id");
        const currentQuestionIdElement = document.getElementById("current-question-id");
        const currentQuestionId = parseInt(currentQuestionIdElement.value, 10);
        const isLastQuestionElement = document.getElementById("is-last-question");
        const isLastQuestion = isLastQuestionElement && isLastQuestionElement.value === 'true';

        if (isNaN(currentQuestionId)) {
            console.error("Invalid currentQuestionId:", currentQuestionIdElement.value);
            return;
        }


        // Store user answers
        const selectedAnswer = document.querySelector('input[name="answer"]:checked');
        if (selectedAnswer) {
            userAnswers[currentQuestionId] = selectedAnswer.value; // Save answer with question ID as key
        }

        let url = `/quiz/${quizId}/next/${currentQuestionId}`;

        if (isLastQuestion) {
            displayScore(quizId);
            return;
        }

        fetch(url).then(response => response.text())
            .then(fragment => {
                const questionContainer = document.getElementById("question-container");
                questionContainer.innerHTML = fragment;
                initPage();
            });
    });
}


// Function to handle displaying the final score
function displayScore(quizId) {

    let url = `/quiz/${quizId}/correct-answers`;
    fetch(url)
        .then(response => {
            if (!response.ok) {
                console.error('Network response was not ok');
                return {};
            }
            return response.json();
        })
        .then(correctAnswers => {
            console.log('correctAnswers:', correctAnswers);

            let score = 0;
            const totalQuestions = Object.keys(correctAnswers).length;

            for (const [questionId, userAnswerId] of Object.entries(userAnswers)) {

                console.log('questionId:', questionId);
                console.log('userAnswerId:', userAnswerId);

                const correctAnswerId = correctAnswers[questionId];

                console.log('correctAnswerID:', correctAnswerId);

                if (parseInt(userAnswerId, 10) === correctAnswerId) {
                    score++;
                }
            }

            // Update resultContainer with the results
            const resultContainer = document.getElementById('question-container');
            resultContainer.innerHTML = `
                <div class="card mb-2 quiz-card">
                    <h1>Quiz Results</h1>
                    <h3>Your Score: <span id="score">${score}</span> out of ${totalQuestions}</h3>
                    <a href="/quiz/${quizId}/start" class="btn btn-dark btn-primary custom-btn custom-hover-btn">Try again?</a>

                    <a href="/" class="btn btn-dark btn-primary custom-btn custom-hover-btn">Return to Home</a>
                </div>
                <input type="hidden" id="quiz-id" value="${quizId}">
            `;
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
}