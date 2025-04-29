# ✅ Checklist de développement - Tamagochi Loufoque 🐛💬

## 🛠️ Mise en place du projet
- [x] Initialiser le projet Maven avec JavaFX
- [x] Créer la structure des dossiers (controller, model, service, utils, resources)
- [x] Ajouter JavaFX au `pom.xml`
- [x] Créer la classe `MainApp.java`

## 🧠 Modèle (model)
- [x] Créer `Lubullule.java` avec ses attributs (faim, énergie, humeur, etc.)
- [x] Créer `Etat.java` (Enum ou classe) pour gérer les différents états
- [ ] Ajouter les méthodes : `manger()`, `jouer()`, `dormir()`, `mettreAJourEtat()`

## 🎮 Logique de jeu (service)
- [ ] Créer `GameLogic.java` pour gérer l’évolution de l’état dans le temps
- [ ] Ajouter des timers ou un scheduler pour la dégradation automatique
- [ ] Créer `AnimationService.java` pour gérer les transitions visuelles

## 🎨 Interface utilisateur (FXML + contrôleurs)
- [ ] Créer `primary.fxml` avec :
  - [ ] Affichage de l’image (sprite) de la lubullule
  - [ ] Boutons d’action (Nourrir, Jouer, Dormir)
  - [ ] Barres ou textes pour afficher les besoins
- [ ] Créer `PrimaryController.java` pour relier l’interface à la logique
- [ ] Relier `MainApp.java` à `primary.fxml`
- [ ] Ajouter un deuxième écran via `secondary.fxml` (optionnel)

## 🖼️ Apparence & Graphisme
- [ ] Créer/chercher des sprites pour chaque état de la lubullule
  - [ ] Heureuse
  - [ ] Fatiguée
  - [ ] Affamée
  - [ ] En colère
  - [ ] Endormie
- [ ] Mettre les images dans `resources/images`
- [ ] Créer `SpriteLoader.java` pour charger dynamiquement les sprites
- [ ] Ajouter un fichier `styles.css` pour styliser l’interface

## 🔊 (Optionnel) Sons
- [ ] Ajouter des sons rigolos (dans `resources/sounds`)
- [ ] Jouer un son quand on clique sur un bouton ou change d’état

## 🧪 Tests & Ajustements
- [ ] Vérifier que chaque bouton affecte bien l’état
- [ ] Tester les transitions automatiques d’états
- [ ] Corriger les bugs ou incohérences

## 🎁 Bonus / Extensions possibles
- [ ] Personnaliser le nom de la lubullule
- [ ] Ajouter un mode "jour/nuit"
- [ ] Sauvegarder et charger la partie
- [ ] Ajouter des mini-jeux
- [ ] Changer de décor (thème de fond)

---

