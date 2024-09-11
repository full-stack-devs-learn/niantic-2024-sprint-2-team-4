# Trivio Quiz App

# Project Overview
The Trivio Quiz App is a web-based application designed to provide users with an interactive quiz-taking experience. Users can select a quiz from the home page, answer questions sequentially, and view their final scores at the conclusion of each quiz. The application is built with Java, Spring Boot, Thymeleaf, JavaScript, and the Fetch API to enable dynamic question loading.

This project was developed using pair programming, where team members alternated between driving and navigating roles. Task management was facilitated using a Trello board, while LucidChart was employed to create design diagrams and plan the application's architecture.

# Key Technologies and Tools
- **Programming Languages:** Java, JavaScript
- **Frameworks:** Spring Boot (Backend), Thymeleaf (Frontend Templating)
- **APIs:** Fetch API (Dynamic Data Loading)
- **Database:** MySQL
- **Version Control:** Git (GitHub)
- **Project Management:** Trello (Task Management)
- **Design Tools:** LucidChart (Flowcharts and Diagrams)
- **Development Environment:** IntelliJ IDEA CE
- **Testing:** Browser DevTools

# Key Features
- **Quiz Selection:** Users can choose from various quizzes available on the home page.
- **Dynamic Question Loading:** JavaScript and the Fetch API are utilized to load questions dynamically, enhancing the user experience.
- **Score Display:** The user's total score is displayed at the end of each quiz, providing immediate feedback.

# Development Process: Planning and Design
The development process followed a structured approach, guided by a Kanban board in Trello. The project was divided into manageable tasks to monitor progress and maintain focus throughout the development cycle.

Lucidchart was used to create flowcharts and diagrams, helping visualize the application's architecture, data flow, and the layout of each page to ensure a coherent design strategy.

# Key Development Stages
The project was executed following a ten-step development plan:

- **1. Setup:** Cloned the repository, ran the provided database script, and ensured a stable connection between the database and the application. 
- **2. Project Planning:** Familiarized ourselves with the data in our database. Conducted brainstorming sessions to define goals and create visual diagrams for page layouts and functionality. Tasks were broken down and prioritized on Trello, allowing effective time management and focus on critical features.
- **3. Data Access Objects (DAO):** Developed the DAO methods to handle database interactions, including retrieving quizzes, questions, and possible answers.
- **4. Controllers:** Implemented controllers to manage HTTP requests and handle data flow between the front end and the back end.
- **5. HTML Development:** Created the basic HTML structure for the home and quiz pages.
- **6. JavaScript Functionality:** Developed JavaScript to manage quiz logic, including loading the selected quiz, initiating the quiz with a start button, dynamically loading questions using the Fetch API, tracking the user's score, and implementing a next button to proceed to subsequent questions.
- **7. Styling:** Designed the user interface for a more intuitive and engaging user experience.
- **8. Content Expansion:** Populated the database with additional quizzes to provide a richer user experience.
- **9. Testing:** Conducted user testing internally and with family members to ensure a smooth and enjoyable experience.
- **10. Code Optimization:** Refactored the codebase to enhance readability, maintainability, and performance.

# Challenges Encountered
Several challenges were addressed during development, including:

- **Backend Challenge:** Ensuring the "next" button was only displayed after an answer was selected.
  - **Problem:** 
  - **Approach:** 
  - **Solution:** 


- **Backend Challenge:** Accurately calculating and displaying the final score on the results page.
  - **Problem:** 
  - **Approach:** 
  - **Solution:** 


- **Frontend Challenge:** Enabling the radio button to select an answer when the user clicked on the answer's text.
  - **Problem:** The radio button behavior was incorrect: clicking on any answer text would select the first answer’s radio button. 
  - **Approach:** Debugged the JavaScript to ensure that the value attribute of the radio buttons was being correctly set and retrieved.
  - **Solution:** Updated the JavaScript to correctly fetch the selectedAnswerId from the radio button group, ensuring that the value is captured accurately and passed to the backend.


- **Frontend Challenge:** Implementing a progress bar that accurately reflects the user's progress through the quiz.
  - **Problem:** The progress bar would remain empty until the very last question and then it would be 100%. 
  - **Approach:**  Investigated the JavaScript logic responsible for updating the progress bar, focusing on how it was being calculated and displayed.
  - **Solution:** Refactored the JavaScript code to properly calculate and update the progress bar percentage based on the total number of questions and the current question index.



# Favorite Block of Code
- **Tabatha’s:** 

- **Valerie’s:** 

# Acknowledgments
We extend our gratitude to our instructor, Gregor, for his invaluable guidance and support throughout this project. Additionally, we appreciate the constructive feedback provided by our peers during our project presentation.