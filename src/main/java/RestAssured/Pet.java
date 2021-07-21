
package RestAssured;

import java.util.List;
import com.google.gson.annotations.Expose;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class Pet {


    private Category category;
    private Long id;
    private String name;
    private List<String> photoUrls;
    private String status;
    private List<Tag> tags;

}
