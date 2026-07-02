package dev.webshop.winkelmanden;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record VoegWinkelmandLijnToeRequest (
        @NotNull @Positive Long artikelId
        , @NotNull @Positive Integer aantal
) {
}
