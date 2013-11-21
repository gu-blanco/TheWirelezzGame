The Wirelezz Game
==================

Aplicativo Android. Permite se conectar a um servidor do Wirelezz Game para jogar.

Importando o projeto
---------------------

1. Baixe o repositório em uma pasta.
2. Baixe o **Google Play Services** e a **Android Support Library** usando o Android SDK Manager
3. Entre na pasta do projeto do Google Play Services **android-sdks\extras\google\google_play_services\libproject\ **
4. Copie a pasta **google-play-services_lib** para a mesma pasta que o projeto foi baixado.
5. Entre na pasta do projeto do Android Support Library v7 **android-sdk\extras\android\support\v7\ **
6. Copie a pasta **appcompat** para a mesma pasta que o projeto foi baixado.
7. Importe os projetos no Eclipse usando: *Import > Android > Existing Android Code into Workspace*. Renomeie o projeto se estiver com o nome errado.
8. Clique com o botão direito no projeto do The Wirelezz Game, clique em Properties, clique em Android, na parte de Library clique em Add e selecione o projeto *google-play-services_lib* e *android-support-v7-appcompat*. Um marcador verde deverá indicar que está importado corretamente.
9. Ainda nas propriedades do projeto vá em Java Build Path, na aba Projects, adicione os dois projetos das bibliotecas.
10. Clique com o botão direito no projeto "android-support-v7-appcompat" e clique em "propriedades"
11. Selecione "Java Build Path"
12. Abra a aba "Order and Export"
13. Marque a opção "Android Private Libraries" and click.
14. Pronto. Rode o projeto para verificar se está tudo certo.

### Renomeando o Projeto
- Clique com o botão direito no projeto, vá em Refactor -> Rename.

### Aplicando Git no Projeto
- Instale o plugin o EGit para Eclipse.
- Clique com o botão direito no projeto, Team -> Share Project...
- Selecione Git, clique Next, o projeto será reconhecido com o Git, clique Finish.