# OpenTetris
Alterações realizadas:
Função restart com aplicação nas classes frame e board;
Teclas de atalho com aplicação nas classes frame e board;
Refatoração das classes que possuiam código duplicado/ morto;
Comentários das rotinas e implementações;

Alteração não Realizada:
Alteração relacionada a usabilidade e interface da aplicação, por existir uma lógica complexa para renderizar e posicionar as imagens que geram o jogo.Para tais alterações é necessário uma refatoração do jogo, bem como todos os calculos que ele utiliza para gerar layout do game.
O Ideal é que for realizado uma refatoração seja utilizado um framework do java atualizado e que possua mais recursos para ajustes em geral, como por exemplo Quarkus.

Como executar o jogo: Clonar o projeto, e executar o mesmo pela idea através da classe frame.

Classes que foram retirados códigos duplicados e obsoletos:
Frame
ControlWindows
Board
TetrisPanel
Shape
