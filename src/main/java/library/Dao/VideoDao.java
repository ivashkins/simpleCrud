package library.Dao;

import library.Model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VideoDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public VideoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Video> index(){
        return jdbcTemplate.query("SELECT * FROM Video", new BeanPropertyRowMapper<>(Video.class));
    }

    public Video show(int id) {
        return jdbcTemplate.query("SELECT * FROM Video WHERE Id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Video.class)).stream().findAny().orElse(null);
    }

    public void save(Video video) {
        jdbcTemplate.update("INSERT INTO Video VALUES (?,?,?,?,?)",video.getId(),video.getName(),video.getTime(),video.getLink(),video.getIssue());
    }

    public void update(int id, Video video) {
        jdbcTemplate.update("UPDATE Video SET name=?,time=?,link=?,issue=? WHERE Id=?",video.getName(),video.getTime(),video.getLink()
                ,video.getIssue(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Video WHERE Id=?",id);
    }

}
