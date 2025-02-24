<h1 align="center">
  <p align="center">Movie2You</p>

## 💻 Projeto

Movie2You é um aplicativo Android que permite aos usuários explorar o mundo dos filmes. Utilizando
a [TMDB API](https://developer.themoviedb.org/docs/getting-started), o app exibe listas de filmes
de diversas categorias e fornece detalhes sobre cada título, incluindo comentários e
filmes semelhantes.

https://github.com/user-attachments/assets/ab8d7b9a-dddf-493d-94b4-929261e1f960

</h1>

## 🚀 Funcionalidades

- **Listagem de Filmes**: Catálogo de filmes organizados em quatro categorias: Em Exibição, 
Em Breve, Mais Populares e Melhores Avaliados.

- **Detalhes dos Filmes**: Tela com informações detalhadas sobre o filme, incluindo comentários e 
sugestões de títulos semelhantes.

- **Cache por Sessão**: Após uma requisição bem-sucedida, os dados são armazenados em cache até o 
encerramento do aplicativo.

## 🛠️ Ferramentas de Desenvolvimento

- **IDE**: Android Studio;
- **Linguagem**: Kotlin;
- **Arquitetura**: MVVM com princípios de Clean Architecture;
- **UI**: Jetpack Compose;
- **Injeção de Dependências**: Hilt;
- **Testes**: JUnit, Mockito, Truth, Turbine;

## 💿 Instalação

1. Clone o repositório:

```bash
git clone https://github.com/WallaceMartinsTI/Movie2You.git
```

2. Abra o projeto no Android Studio.

3. Execute o `build` e rode o aplicativo em um emulador ou dispositivo físico.

## 🧾 Notas Finais

O desenvolvimento do Movie2You me permitiu aprimorar minhas habilidades com consumo de API,
organização de código e boas práticas de desenvolvimento. Além disso, trabalhei com
requisitos específicos de layout e prazos de entrega.

Para acompanhar o processo de desenvolvimento, consulte a aba "Issues" deste repositório, onde 
documentei algumas etapas importantes.

O aplicativo foi testado nos seguintes dispositivos:

- **Xiaomi Redmi Note 13** (Physical Device);
- **Pixel 8 Pro** (AVD - Android Virtual Device | Android API 35 x86_64);
- **Pixel 4** (AVD - Android Virtual Device | Android API 29 (Android 10 ("Q")) x86_64);

Sobre o vídeo de demonstração:

- Algumas falhas foram propositalmente adicionadas para demonstrar possíveis erros no app. Essas
  modificações não estão presentes na versão final.

- Os comentários dos filmes na tela de detalhes estão em inglês, pois a API retorna um volume maior
  e mais relevante de feedbacks quando `language=en-US` é utilizado. Em português, os comentários
  disponíveis eram, em sua maioria, genéricos.

- A qualidade do vídeo foi reduzida para viabilizar o upload no GitHub. Além disso, as transições
  entre os segmentos "APP SIMULANDO ERROS" e "APP VERSÃO FINAL" ficaram simples devido à minha falta
  de experiência com edição de vídeo, que não era o foco do projeto.
