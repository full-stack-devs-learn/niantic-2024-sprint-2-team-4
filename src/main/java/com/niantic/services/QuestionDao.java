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
            SELECT question_id,
                quiz_id,
                question_number,
                question_text
            FROM question
            WHERE quiz_id = ? 
              AND question_number > (
                SELECT question_number
                FROM question
                WHERE question_id = ?
            )
            ORDER BY question_number
            LIMIT 1
        """;

        var row = jdbcTemplate.queryForRowSet(sql, quizId, currentQuestionId);

        if (row.next()) {
            return mapRowToQuestion(row);
        } else {
            return null;
        }
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