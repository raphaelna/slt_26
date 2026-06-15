# Projektdokumentation – Tic Tac Toe

## 1. Allgemeines & Repository
* **Projektname:** Tic Tac Toe Game
* **Lehrveranstaltung:** Software Lifecycle Tooling
* **Gruppe / Autoren:** Saidfiasal KHAWARIN, Njegos MILANKOVIC, Raphael NAJFAR
* **Repository-Link (Must-Have):** https://github.com/raphaelna/slt_26

---

## 2. Der Software Lifecycle & Workflow (20 Punkte)

### Prozessbeschreibung: Was passiert, wenn mir eine User Story zugewiesen wird?
Um eine hohe Code-Qualität und lückenlose Nachvollziehbarkeit im Team zu gewährleisten, folgt jede Entwicklung einem strikten, automatisierten Workflow:

1. **Zuweisung & Board-Update:** Die entsprechende User Story im Kanban-Board wird der zuständigen Person zugewiesen und von *Backlog* oder *Ready* in die Spalte *In progress* verschoben.
2. **Branch-Erstellung:** Auf der lokalen Umgebung wird ein dedizierter Git-Branch erstellt. Wir nutzen dafür saubere Feature-Branches, die sich an unsere Namenskonvention halten (z. B. `feature/setup-checkstyle`).
3. **Implementierung & Testen:** Das Feature wird im Quellcode umgesetzt. Gleichzeitig werden pro Methode mindestens zwei Unit-Tests geschrieben, um sowohl positive als auch negative Szenarien abzudecken.
4. **Push & CI-Prüfung:** Der lokale Branch wird zu GitHub gepusht. Dies triggert sofort die automatische **CI-Pipeline** (`Java CI`), die das Projekt baut und alle Tests ausführt.
5. **Pull Request (PR):** Verläuft der CI-Build fehlerfrei (grünes Häkchen), wird ein Pull Request gegen den `main`-Branch geöffnet. Die zugehörige Karte im Kanban-Board wandert automatisch oder manuell in die Spalte *In review*.
6. **Code Review & Merge:** Mindestens ein weiteres Teammitglied prüft die Änderungen im PR. Nach erfolgreichem Review wird der Branch in den `main`-Branch gemergt.
7. **CD-Pipeline & Projektabschluss:** Der Merge auf `main` stößt die **CD-Pipeline** (`Java CD`) an, welche das fertige, ausführbare JAR-File baut und als GitHub-Artifact sichert. Das zugehörige Issue wird geschlossen und die Karte auf dem Kanban-Board in die Spalte *Done* verschoben.

### Exemplarischer Ablauf einer User Story

#### Beispiel: Issue #3 (Ergebnisbenachrichtigung)
> **User Story:** *„As a player, I want to be notified when the game has ended in a win, loss or draw, so that I can see the result of the game.“*

Anhand der Ticket-Historie lässt sich der vollständige Lebenszyklus transparent nachvollziehen. Nach der initialen Erstellung und automatischen Einsortierung ins Backlog wurde das Ticket durch die Entwicklungsphasen bewegt, im Zuge eines Code-Reviews geprüft und nach dem erfolgreichen Merge des Feature-Branches geschlossen.

![[Pasted image 20260615113715.png]]

---

## 3. DevOps & Automatisierung (20 Punkte)

### Das Kanban-Board
Die Steuerung aller Anforderungen und User Stories erfolgte agil über das integrierte GitHub Project Board. Alle für den Release relevanten Issues wurden vollständig abgearbeitet, erfolgreich gereviewt und in die Spalte **Done** überführt.

![[Pasted image 20260615113759.png]]

### CI/CD Pipelines (GitHub Actions)
Die Automatisierung unseres Software-Lifecycles wird durch zwei getrennte Workflows im Ordner `.github/workflows/` gesteuert:

* **Java CI (Branch Verification - `ci.yml`):** Triggert bei jedem Push auf einen Feature-Branch sowie bei der Erstellung von Pull Requests gegen `main`. Führt automatisiert `mvn clean test` aus. Ein Merge in den Hauptzweig ist blockiert, solange dieser Build fehlschlägt.
* **Java CD (Production Build - `cd.yml`):** Triggert exklusiv bei einem erfolgreichen Merge auf den `main`-Branch. Führt den finalen Produktions-Build aus (`mvn clean package -DskipTests`) und lädt das generierte JAR-File als versioniertes GitHub-Artifact hoch.

---

## 4. Git-Konventionen (10 Punkte)

Um die kollaborative Arbeit nachvollziehbar zu gestalten, setzen wir auf strikte Git-Richtlinien:

* **Branch-Struktur:** Es wird niemals direkt auf dem `main`-Branch entwickelt. Jede Änderung erfordert einen eigenen, isolierten Feature-Branch (z. B. `feature/setup-checkstyle`).
* **Commit-Aussagekraft:** Wir arbeiten mit strukturierten Commit-Nachrichten nach dem *Conventional Commits*-Prinzip. Die Zuordnung zu den entsprechenden Issues wird direkt in der Nachricht festgehalten:
  * `feat(#1): implement TicTacToe game logic`
  * `chore: add checkstyle and maven setup`

---

## 5. Testing & Implementation (20 Punkte)

* **Lauffähigkeit:** Das Projekt basiert auf Maven und lässt sich nach dem Klonen mittels `mvn clean package` ohne manuelle Anpassungen fehlerfrei bauen und ausführen.
* **Testabdeckung & -strategie:** Für die Kernkomponenten der Spiellogik wurden durchgängig automatisierte Unit-Tests implementiert. Gemäß den Qualitätsvorgaben deckt jeder Testfall ab:
  * **Positive Testfälle:** Überprüfung des regulären Verhaltens (z. B. korrekte Platzierung eines Symbols auf einem freien Feld, Erkennung eines legitimen Gewinns).
  * **Negative Testfälle:** Überprüfung der Fehlerbehandlung und Robustheit (z. B. versuchtes Überschreiben eines bereits besetzten Feldes, Abfangen ungültiger Koordinaten).
* **Build-Konfiguration:** Alle Test-Abhängigkeiten (z. B. JUnit) sowie Plugins zur Testausführung sind sauber in der zentralen `pom.xml` hinterlegt und vollständig in die CI-Pipeline integriert.