# TODO

## Planned features

- [ ] **AI opponent** — minimax with alpha-beta pruning, configurable difficulty. `Player.java` is already shaped (name + symbol) to support a `human vs AI` distinction without breaking existing callers.
- [ ] **UI/UX overhaul** — visuals, animations, polish beyond the current functional Swing grid.
- [ ] **Multiplayer/networking** — online or LAN play, client-server split.
- [ ] **New game modes & tile types** — beyond the current obstacle-wall maps in `MapDesign.java`.

## Smaller follow-ups

- [ ] Generate the Maven Wrapper (`mvnw`/`mvnw.cmd`) so the repo is buildable without a local Maven install.
- [ ] Add `GameControllerTest` (turn switching, `reset()` producing a fresh empty board).
- [ ] Add a `.gitattributes` to normalize line endings (mixed LF/CRLF showed up when this repo was restructured).
