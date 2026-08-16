package gr.aueb.cf.schoolapp10.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record TeacherEditDTO(
        @NotNull
        UUID uuid,

        @NotNull
        @Size(min = 2)
        String firstname,

        @NotNull
        @Size(min = 2)
        String lastname,

        @Pattern(regexp = "\\d{9,}", message = "Ο ΑΦΜ πρέπει να περιέχει 9 ψηφία. ")
        String vat,

        @NotNull
        Long regionId

) {
}
