const answers = [];

document.addEventListener('DOMContentLoaded', function() {
    initPage();
 });

function initPage(){
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


        //get answer logic, and then plug it into the span in result.html




        let url = `/quiz/${quizId}/next/${currentQuestionId}`;

        if (isLastQuestion) {
            url = `/quiz/${quizId}/result`;
        }

        fetch(url).then(response => response.text())
                    .then(fragment => {
                        const questionContainer = document.getElementById("question-container");
                        questionContainer.innerHTML = fragment;
                        initPage();
                    })
    });
};

