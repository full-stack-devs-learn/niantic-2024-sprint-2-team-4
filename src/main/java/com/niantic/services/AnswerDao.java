package com.niantic.services;

import com.niantic.models.Answer;
import com.niantic.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerDao
{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AnswerDao(DataSource dataSource)
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Answer> getAnswersByQuestionId(int questionId)
    {
        List<Answer> answers = new ArrayList<>();

        String sql = """
            SELECT answer_id,
                question_id,
                answer_text,
                is_correct
            FROM answer
            WHERE question_id = ?
            """;

        var row = jdbcTemplate.queryForRowSet(sql,questionId);

        while(row.next())
        {
            var answer = mapRowToAnswer(row);
            answers.add(answer);
        }
        return answers;
    }

    public List<Answer> getCorrectAnswersByQuestionId(int questionId) {
        String sql = """
                SELECT answer_id,
                    question_id,
                    answer_text,
                    is_correct
                FROM answer
                WHERE question_id = ? AND is_correct = 1
                """;

        List<Answer> correctAnswers = new ArrayList<>();
        var row = jdbcTemplate.queryForRowSet(sql, questionId);

        while(row.next())
        {
            Answer answer = mapRowToAnswer(row);
            correctAnswers.add(answer);
        }
        return correctAnswers;
    }


    private Answer mapRowToAnswer(SqlRowSet rowSet) {
        int answerId = rowSet.getInt("answer_id");
        int questionId = rowSet.getInt("question_id");
        String answerText = rowSet.getString("answer_text");
        boolean isCorrect = rowSet.getBoolean("is_correct");

        return new Answer(answerId, questionId, answerText, isCorrect);
    }
}
