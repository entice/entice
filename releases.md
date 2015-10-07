## Releases

**Todo's during a release:**

For the server-side:

- check the server repo versions, they should be semantically versioned
- check the server repos deps and how they are referenced,
either git commit or tags, no master/develop w/e branches!
- create PRs for develop-to-master deployment, review
- merge PRs, update dep git refs if needed
- git-tag the release version from master
- push branches and tags, clean-up old branches

For the docs:

- make sure the docs comply with the release version
- update the milestone/version information
- git-tag milestone/version and push branches & tags

For the client:

- make sure everything is pushed and testet against staging
- git-tag milestone/version and push brnached & tags


### Milestones

Milestones are either called "MS" the milestone number,
or "0." + milestone number + ".0" to comply with semantic versioning.


### Major Versions

There are no major versions yet.
