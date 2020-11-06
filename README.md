# sonda-demo-elo7

## Explorando Marte
O sistema desenvolvido é uma API que simula a exploração de uma área em marte. O software está preparado para receber os pontos máximos X e Y, para montar assim 
a área que será explorada pelas sondas. Logo em seguida receberá, em sequência, as informações sobre a posição inicial que cada sonda será lançada e o caminho
que cada sonda irá realizar. Assume-se aqui que cada sonda fará sua exploração e permanecerá na área parada quando as ordens de seu caminho terminar, sendo só 
depois que uma que uma sonda terminar o seu percuso, que uma outra sonda será lançada, caso houver mais sondas para realizar a exploração. Presume-se que as 
sondas serão recolhidas após todas realizarem seus trajetos.

## Premissas
Uma sonda não poderá em nenhum momento, ocupar um espaço já ocupado por outra sonda e nem ultrapassar a área definida para exploração. 

Para que a sonda nunca ultrapasse a área de exploração, o sistema não conseguirá atribuir um valor maior que o X ou o Y da área.
Sendo assim, sempre que uma informação de caminho for averiguada e a ela resultar em um ponto fora da ára de exploração, a sonda não irá se movimentar, 
passando para a pŕóxima informação de caminho. Isso evita que uma sonda se extravie. 

Algo similar acontece se uma sonda encontrar outra,que já cumpriu seu movimento, parada. 

O sistema também não permite o lançamento das sondas se alguma delas possuírem valores iniciais fora da área de exploração, extourando uma excessão Bad Request. 

## Arquitetura do Projeto
O sistema foi desenvolvido como uma API Rest, visando uma futuras integrações front end, mobile ou mesmo outras APIs. 

Foi utilizado padrão MVC e SpringBoot. 

O planejamento desse microsserviço leva em consideração a arquitetura de Domain Driven Design.

 
