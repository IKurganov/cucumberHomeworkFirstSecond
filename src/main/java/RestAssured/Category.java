
package RestAssured;

import com.google.gson.annotations.Expose;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class Category {

    private Long id;
    private String name;

}
