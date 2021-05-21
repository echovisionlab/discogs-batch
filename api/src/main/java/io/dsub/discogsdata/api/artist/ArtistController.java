package io.dsub.discogsdata.api.artist;

import io.dsub.discogsdata.api.exception.ArtistNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artists")
@RequiredArgsConstructor
public class ArtistController {

  private final ArtistService artistService;

  @GetMapping
  public List<ArtistDto> getArtists() {
    return artistService.getArtists();
  }

  @GetMapping("/{id}")
  public ArtistDto getArtistById(@PathVariable(name = "id") long id)
      throws ArtistNotFoundException {
    return artistService.getArtistById(id);
  }

  @GetMapping(params = "name")
  public List<ArtistDto> getArtistsByNameLike(String name) {
    return artistService.getArtistsByName(name);
  }
}
