package com.niantic.services;

import com.niantic.models.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuizDao
{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public QuizDao(DataSource dataSource)
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Quiz> getAllQuizzes()
    {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        String sql = """
            SELECT quiz_id
                , quiz_title
                , is_live
                , description
            FROM quiz;
        """;
        var row = jdbcTemplate.queryForRowSet(sql);
        while (row.next())
        {
            var quiz = mapRowToQuiz(row);
            quizzes.add(quiz);
        }

        return quizzes;
    }

    public Quiz getQuizById(int quizId)
    {
        String sql = """
                SELECT quiz_id,
                    quiz_title,
                    is_live,
                    description
                FROM quiz
                WHERE quiz_id = ?
        """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, quizId);

        if(row.next())
        {
            String quizTitle = row.getString("quiz_title");
            boolean isLive = row.getBoolean("is_live");
            String description = row.getString("description");

            return new Quiz(quizId, quizTitle, isLive, description);
        }
        else {
            return null;
        }
    }

    private Quiz mapRowToQuiz(SqlRowSet row)
    {
        int id = row.getInt("quiz_id");
        String title = row.getString("quiz_title");
        boolean isLive = row.getBoolean("is_live");
        String description = row.getString("description");

        return new Quiz(id, title, isLive, description);
    }
}
