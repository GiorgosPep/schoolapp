package gr.aueb.cf.schoolapp10.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//  TODO Localization
public record TeacherInsertDTO(
        @NotNull(message = "Το όνομα δε μπορεί να είναι κενό. ")
        @Size(min = 2, message = "Το όνομα πρέπει να περιέχει τουλάχιστον δύο χαρακτήρες. ")
        String firstname,
        @NotNull(message = "Το επώνυμο δε μπορεί να είναι κενό. ")
        @Size(min = 2, message = "Το επώνυμο πρέπει να περιέχει τουλάχιστον δύο χαρακτήρες. ")
        String lastname,
        @Pattern(regexp = "\\d{9,}", message = "Ο ΑΦΜ πρέπει να περιέχει 9 ψηφία. ")
        String vat,
        @NotNull(message = "Η περιοχή δε μπορεί να είναι κενή. ")
        Long regionId

) {
    public static TeacherInsertDTO  empty() {
        return new TeacherInsertDTO("","","",0L);
    }
}
