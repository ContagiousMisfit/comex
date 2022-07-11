# Comex
![bootcamp-alura-ciandt](https://user-images.githubusercontent.com/52979585/178312542-632bd826-43f8-4860-b46b-2689f501bc46.gif)

<h2>✨ Indice</h2>
<a href="#sobre"> Sobre </a>
<br>
<a href="#clean-arch"> Conceito de Clean Arch </a>
<br>
<a href="#ddd"> Conceito de DDD </a>
<br>
<a href="#aws"> Arquitetura Cloud - AWS </a>
<br>
<a href="#aws-ms"> Arquitetura de microsserviços - AWS </a>

<h1></h1>
<p id="sobre"> O projeto Comex é um sistema de e-commerce marketplace. Seu objetivo é permitir que a sua base de clientes tenha acesso
a vários vendedores e, assim, consolidar-se como o shopping eletrônico mais popular da internet.</p>
<p> O sistema desenvolvido é uma <b>API REST</b> funcional, com testes unitários para todos os endpoints e que conta com um sistema de autenticação utilizando JWT. </p>
<h3>Tecnologias e plataformas</h3>

<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"><img src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot"><img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white"><img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white"><img src="https://img.shields.io/badge/Amazon_AWS-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white"><img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white"><img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white"><img src="https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white"><img src="https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"><img src="https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white"><img src="https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white"><img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white"><img src="https://img.shields.io/badge/Heroku-430098?style=for-the-badge&logo=heroku&logoColor=white">

<br>

<h1 id="clean-arch"> 👩🏼‍💻 DDD e Clean Arch na Aplicação </h1>
A separação de camadas escolhida vai nos poupar de problemas futuros com a manutenção do software.
<br>
<br>

![diagrama_camadas](https://user-images.githubusercontent.com/52979585/176940825-b37634de-49ee-44d5-a929-a441fe65a8ed.png) 

<h1></h1>
<h1 id="ddd"> ⚡ Quais os agregados da aplicação, qual sua raiz e que classes os compõem? </h1>

>Segundo Martin Fowler, Aggregate é um padrão no Domain-Driven Design e "um cluster de objetos de domínio que podem ser tratados como uma única unidade.
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
![diagrama-contextos](https://user-images.githubusercontent.com/52979585/177211190-5b6a0284-2516-44f5-a00e-279b38268de1.png)

<h1 id="aws">☁️ Arquitetura Cloud - AWS</h1>

![AWS-architecture](https://user-images.githubusercontent.com/52979585/178041190-af100332-2b60-4ed7-8180-153ccb9fcc5f.png)

<h2> Componentes da arquitetura </h2>

- **API Gateway:**
Esse serviço vai atuar como porta de entrada única (nada de fora do VPC consegue acessar a aplicação sem passar por ele), além de servir como um mecanismo de segurança. 
O API Gateway vai garantir a proteção por meio da autenticação de usuário, limitação de conexões e fornecer logs de acesso.

<h1 id="aws-ms">☁️ Arquitetura de Microsserviços na AWS</h1>

![AWS-MS](https://user-images.githubusercontent.com/52979585/178319139-dce8c271-3889-422b-8ce6-6f420350cb38.png)
