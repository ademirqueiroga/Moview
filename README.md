### Protótipo gráfico

Imagens do protótipo na pasta [prototipo](prototipo).

Splash | Login | Feed
:-:|:-:|:-:
![](/prototipo/app1.jpeg) | ![](/prototipo/app2.jpeg) | ![](/prototipo/register.jpeg) 

Feed | Buscar Categoria | Buscar Título | Meu Perfil
:-:|:-:|:-:|:-:
![](/prototipo/app3.jpeg) | ![](/prototipo/app4.jpeg) | ![](/prototipo/app5.jpeg) | ![](/prototipo/app6.jpeg) 

### Crud de entidades
* Movie
* Category
* Post
* Comment
* User

As entidades listadas acima serão armazenadas na aplicação para uma melhor experiência do usuário e serão atualizadas sempre que necessário.

### Internacionalização
A internacionalização consiste de dois arquivos de resources _strings.xml_ um arquivo padrão (default) com os valores das strings em inglês e outro arquivo destinado a dispositivos configurados com a lingua portuguesa com os valores traduzidos para o português. Caso o dispositivo utilize uma linguagem diferente do PT-BR o app usará o arquivo padrão. Os arquivos estão separados nas pastas _values_ e _values-pt_, ambos dentro da pasta [res](app/src/main/res).

### Integração com serviço externo
O aplicativo utilizará a API de filmes [The Movie Database](https://www.themoviedb.org/documentation/api) onde consultará todas as  informações relacionadas aos filmes e categorias. Além dessa API o app irá se comunicar com uma api desenvolvida específicamente para ele onde será registrado os comentários, posts e informações dos usuários.

### Recursos da plataforma
* Login Facebook
* Reprodutor de mídia nativo

O app irá utilizar o reprodutor de vídeos nativo do Android para exibir trailers dos filmes e também irá utilizar a [sdk do Facebook](https://developers.facebook.com/docs/android/) para realizar login.
