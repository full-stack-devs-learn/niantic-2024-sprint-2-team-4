package com.niantic.services;

import com.niantic.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionDao
{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public QuestionDao(DataSource dataSource)
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // finding the quiz to retrieve the questions from
    public List<Question> getQuestionsByQuizId(int quizId) {
        String sql = """
            SELECT question_id, 
                quiz_id,
                question_number,
                question_text 
            FROM question
            WHERE quiz_id = ?
            ORDER BY question_number
        """;
        List<Question> questions = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, quizId);

        while (rowSet.next()) {
            questions.add(mapRowToQuestion(rowSet));
        }
        return questions;
    }

    public Question getQuestionsById(int questionId) {
        String sql = """
            SELECT question_id,
                quiz_id,
                question_number,
                question_text
            FROM question
            WHERE question_id = ?
        """;

        var row = jdbcTemplate.queryForRowSet(sql, questionId);

        if (row.next()) {
            return mapRowToQuestion(row);
        }
        else
        {
            return null;
        }
    }

    // Finding the first question of the quiz that was requested
    public Question getFirstQuestion(int quizId) {
        String sql = """
            SELECT question_id,
                quiz_id,
                question_number,
                question_text
            FROM question
            WHERE quiz_id = ?
            ORDER BY question_number
            LIMIT 1
        """;

        var row = jdbcTemplate.queryForRowSet(sql, quizId);

        if (row.next()) {
            return mapRowToQuestion(row);
        } else {
            return null;
        }
    }

    // Get the next question based on currentQuestionId
    public Question getNextQuestion(int quizId, int currentQuestionId) {
        String sql = """
            SELECT question_number
            FROM question
            WHERE question_id = ?
        """;

        Integer currentQuestionNumber = jdbcTemplate.queryForObject(sql, Integer.class, currentQuestionId);

        if (currentQuestionNumber == null) {
            return null;
        }

        // displays the next question information (ie. what number the user is on, and displays the question itself)
        String nextQuestionQuery = """
            SELECT question_id,
                question_number,
                question_text
            FROM question
            WHERE quiz_id = ?
                AND question_number > ?
            ORDER BY question_number
            LIMIT 1
        """;

        // rowNum not used, but represents row indexes
        Question nextQuestion = jdbcTemplate.queryForObject(nextQuestionQuery, (rs, rowNum) -> new Question(
                rs.getInt("question_id"),       // extracts value of question_id
                quizId,
                rs.getInt("question_number"),
                rs.getString("question_text")
        ), quizId, currentQuestionNumber);

        return nextQuestion;
    }

    public int getTotalQuestions(int quizId)
    {
        String sql = """
        SELECT COUNT(*)
        FROM question
        WHERE quiz_id = ?
        """;

        var row = jdbcTemplate.queryForRowSet(sql, quizId);
        row.next();
        return row.getInt(1);

    }

    // Maps all the variables in question table to make them easily retrievable
    private Question mapRowToQuestion(SqlRowSet rowSet) {
        int questionId = rowSet.getInt("question_id");
        int quizId = rowSet.getInt("quiz_id");
        int questionNumber = rowSet.getInt("question_number");
        String questionText = rowSet.getString("question_text");

        return new Question(questionId, quizId, questionNumber, questionText);
    }

}