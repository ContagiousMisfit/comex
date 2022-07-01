# Comex

O projeto Comex é um sistema de e-commerce marketplace. Seu objetivo é permitir que a sua base de clientes tenha acesso
a vários vendedores e, assim, consolidar-se como o shopping eletrônico mais popular da internet.
<br>

## 👩🏼‍💻 DDD e Clean Arch na Aplicação
A separação de camadas escolhida vai poupar de problemas futuros com a manutenção do software.
<br>
<br>
![diagrama_camadas](https://user-images.githubusercontent.com/52979585/176940825-b37634de-49ee-44d5-a929-a441fe65a8ed.png) 


### ⚡ Quais os agregados da aplicação, qual sua raiz e que classes os compõem?
Segundo Uncle Bob, Aggregate é um padrão no Domain-Driven Design e "um cluster de objetos de domínio que podem ser tratados como uma única unidade."
Um exemplo pode ser um **pedido** e seus **itens**, que serão objetos separados, mas é útil tratar o pedido (junto com seus itens) como um único agregado.

Essa mesma lógica busquei aplicar no Comex. O **ItemDePedido** não possui seu próprio Repository, *não é persistido de forma isolada*. 
Ao manipular os dados, manipulamos unicamente o **Pedido** para *garantir a integridade do agregado como um todo.*

No diagrama, as Entidades (Aggregate roots) são representadas maiores e unidas aos seus Value Objects. 

### 🕵🏼‍♀️ Diferença entre VO e Entity
*Entidades possuem uma **identidade única**, enquanto VOs são considerados iguais, se todos os seus atributos tiverem valores iguais.*

Um Cliente possui uma identidade única -> seu CPF, não existem dois clientes com o mesmo Cadastro de Pessoas Física.

Um Endereço não possui uma identidade única -> duas pessoas podem inserir o mesmo endereço, (mesma rua, cidade, estado).

Um Usuário possui identidade única -> o email é exclusivo por usuário.

![diagrama_entities](https://user-images.githubusercontent.com/52979585/176941973-a02fd639-e156-4377-9c1c-ce4fd5f3789b.png)
