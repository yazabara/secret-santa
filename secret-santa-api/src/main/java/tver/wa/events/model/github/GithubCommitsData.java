package tver.wa.events.model.github;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubCommitsData implements GithubVersion {

    private String sha;
    private GithubCommit commit;
}
