package backend.backend.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.util.List;

@Document(collection = "courses") // Maps to "courses" collection in MongoDB
@Data // Lombok: auto-generates getters, setters, toString, etc.
public class course {
    @Id
    private String id; // Unique ID for each course (MongoDB auto-generates)
    private String title; // e.g., "JavaScript for Beginners"
    private String description; // e.g., "Learn JS basics"
    private String category; // e.g., "Web Development"
    private String learningLevel; // e.g., "Beginner"
    private List<String> materials; // e.g., ["video.mp4", "notes.pdf"]
    private List<String> tags; // e.g., ["javascript", "coding"]
    private String instructorId; // ID of the user who created the course
}