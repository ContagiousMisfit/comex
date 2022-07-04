# Comex

O projeto Comex é um sistema de e-commerce marketplace. Seu objetivo é permitir que a sua base de clientes tenha acesso
a vários vendedores e, assim, consolidar-se como o shopping eletrônico mais popular da internet.
<br>

## 👩🏼‍💻 DDD e Clean Arch na Aplicação
A separação de camadas escolhida vai nos poupar de problemas futuros com a manutenção do software.
<br>
<br>
![diagrama_camadas](https://user-images.githubusercontent.com/52979585/176940825-b37634de-49ee-44d5-a929-a441fe65a8ed.png) 


### ⚡ Quais os agregados da aplicação, qual sua raiz e que classes os compõem?
> Segundo Martin Fowler, Aggregate é um padrão no Domain-Driven Design e "um cluster de objetos de domínio que podem ser tratados como uma única unidade.
Um exemplo pode ser um **pedido** e seus **itens**, que serão objetos separados, mas é útil tratar o pedido (junto com seus itens) como um único agregado."

Essa mesma lógica busquei aplicar no Comex. O **ItemDePedido** não possui seu próprio Repository, *não é persistido de forma isolada*. 
Ao manipular os dados, manipulamos unicamente o **Pedido** para *garantir a integridade do agregado como um todo.*

No diagrama, as Entidades (Aggregate roots) são representadas maiores e unidas aos seus Value Objects. 

### 🕵🏼‍♀️ Diferença entre VO e Entity
>*Entidades possuem uma **identidade única**, enquanto VOs são considerados iguais, se todos os seus atributos tiverem valores iguais.*

Um Cliente possui uma identidade única -> seu CPF, não existem dois clientes com o mesmo Cadastro de Pessoas Física.

Um Endereço não possui uma identidade única -> duas pessoas podem inserir o mesmo endereço, (mesma rua, cidade, estado).

Um Usuário possui identidade única -> o email é exclusivo por usuário.

![diagrama_entities](https://user-images.githubusercontent.com/52979585/176958110-3d755104-9d98-4536-9430-d9c2f1fc2401.png)

### 🔒 Invariante

> "Toda manipulação de dados deve ser feita dentro da aggregate root, para garantir consistência e impedir que outras classes manipulem esses dados." 

Busquei aplicar esse conceito, porém, para evitar nested `if`s, coloquei os use cases de aplicar desconto dentro de suas factories, para que o objeto seja criado aplicando a regra de negócio.

➡️ **PedidoBuilder.java**
```java
    public PedidoBuilder aplicarDesconto() {
    
        if (cliente.getListaDePedidos().size() > 5) {
            this.tipoDesconto = TipoDescontoPedido.FIDELIDADE;
            this.desconto = new BigDecimal(0.5);
            return this;
        }

        this.tipoDesconto = TipoDescontoPedido.NENHUM;
        this.desconto = BigDecimal.ZERO;
        return this;

    }
```
➡️ **ItemDePedidoBuilder.java**
```java
    public ItemDePedidoBuilder aplicarDesconto() {
    
        if (quantidade > 10) {
            this.tipoDesconto = TipoDescontoItemPedido.QUANTIDADE;
            this.desconto = BigDecimal.TEN;
            return this;
        }
            this.tipoDesconto = TipoDescontoItemPedido.NENHUM;
            this.desconto = BigDecimal.ZERO;
        return this;
        
    }
```
## Bounded Contexts

Para resolver conflitos de nomenclatura e classificação dos produtos, vamos separar a aplicação em *dois contextos*, sem um shared kernel.

Na **loja**, a entidade `Produto` tem os atributos: comprimento em mm, altura em mm, largura em mm, peso em gramas que se referem às dimensões  do produto *fora da caixa.*

Já no **estoque**,  para o armazém, a entidade tem os mesmos atributos, mas eles são válidos para *dentro da caixa.*
