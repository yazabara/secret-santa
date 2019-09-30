package tver.wa.model.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface GithubVersion {
    // response interface for version response (from github service)
}
