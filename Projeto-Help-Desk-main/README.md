# Projeto Help Desk

Comandos FRONT

no tsconfig do angular passar o "strict" (habilita a checagem de tipos do typescript) para false para trabalhar com maior flexibilidade de dados, caso campos puderem ser tanto de um tipo quanto de outro

ng add @angular/material : adicionar angular material
npm i --save ngx-mask : dependencia para fazer mascaras como a de cpf (para usar a mask colocque mask="valor" no input que deseja ex: mask="000.000.000-00")
npm i ngx-toastr: é usado para mostrar notificações mais pequenas ao usuario
    no app. module na parte de imports adicione o
        NgxMaskModule.forRoot(),
        ToastrModule.forRoot({
        timeOut:4000,
        closeButton: true,
        progressBar:true
        })
        .forRoot() significa que é usado para qualquer rota

ng g s nome : vai criar um serviço
ng g s nomeDaPasta/nomeDoService

ng g c nome : vai gerar um component de exibicoes
ng g c nomePasta/nomeArquivo

ng g guard nome : ira gerar um guarda que ativa as rotas somente a partir de certa condição do token
    escolher - can activate
    adicione a dependencia do jwt para verificação de tokens
        npm i @auth0/angular-jwt --save

ng g interceptor interceptador : vai gerar um inteceptados de envios rest, é ele quem insere header que voce especifica
ng g interceptor  nomeDaPasta/nomeDoService
    modifique a linha para ficar desta forma
        intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    no metodo a linha que coloca o token no header é a
        const clonarRequisicao = request.clone({headers: request.headers.set('Authorization',`Bearer ${token}`)})
    e a que envia o header junto com a requisição é a 
        return next.handle(clonarRequisicao)

    apos fazer isso crie uma constante, coloque os seguintes atributos
        export const nomeDaConst = [
            {
            provide: HTTP_INTERCEPTORS,
            useClass: nomeDaClasseDoInterceptor,
            multi: true
            }
        ]
        depois coloque essa const no 
            providers: [nomeDaConst],
            que fica no app.module
    
-------------------------------------------------------------------------------------------------------------------------------------
Codigos FRONT

novos imports devem ser feitos no app.module.ts:
    primeiro faça o import dele depois insira na parte de arrays do imports:[]


Quando utilizamos metodos nos services colocamos Observable<Tipo> e o tipo de http para dizer o que sera retornado no para o componente 
  metodo(var:TIPO VAR): Observable<TIPO RETORNO>{
    return this.httpCliente.TIPO HTTP<TIPO RETORNO>(`link`,valor caso seja necessario)
  }

  ao fazermos isso quando chamarmos o metodo a variavel que receber o valor ja ira entender o tipo da mesma
        this.tecnicoService.metodo().subscribe(
            response =>{  -> a var que ja entendera seu tipo
                //codigo
        })

para trabalhar com validações em forms é usado o FormControl nele voce especifica todas as validações que devem acontecer no front
    pode ter uma validação
        var:FormControl = new FormControl(null, Validators.minLength(3))
    ou pode ter um array de validações
        var:FormControl = new FormControl(null, [Validators.required, Validators.minLength(11),Validators.maxLength(14)])

    nisso voce cria um metodo para validar esses campos e trazer um retorno pro front
        metodo():boolean{ :tipoRetorno
            return this.nome.valid && this.cpf.valid && this.email.valid && this.senha.valid
        }
    
    no front voce ira colocar
        
        [formControl]="nome da variavel do form control" -> isso tem que ser colocado no input que voce quer que aconteça a validação

        [disabled]="metodo()" -> ja no botão para executar a ação voce ira colocar o disable e o metodo que ira fazer as validações para que o usuario não possa salvar nada caso não estejam salvos os dados de acordo com o requisito
    
    lembramdo pode ser criadas varias validações e serem colocados em cada input do form

caso deseja trabalhar com formularios sem ngForm deve usar o [(ngModel)] para capturar dados inseridos pelo usuario
    <input [(ngModel)]="variavel que vai ser inserido o valor" name="nome da variavel que vai ser inserido o valor">
    caso esteja usando uma interface ou classe deve fazer
        <input [(ngModel)]="nomeDaVariavelQueHerdaDaClasseOuInterface.nomeDaVariavel" name="nomeDaVariavel">

 if(this.technical.profile.includes(perfil)){ -> verifica se a variavel tem aquele valor (podendo ser de qualquer tipo, number, string etc)
      this.technical.profile.splice(this.technical.profile.indexOf(perfil),1) -> indexOf procura no array o valor especificado splice(valor, numero de itens que quer retirar)

adicionar caminhos é no app-routing.module.ts
    dentro do const routes: Routes = [ insira
        {
            path:'nomeDaRota',component:component que ira aparecer
        },
        
    caso deixe o path vazio ele sera o component padrão que ira aparecer ao iniciar a aplicação

    para fazer uma rota ativada atravez de verificação de token, com dependencias e com paginas redirecionadas atravez da denominada pai de todas então coloque
        {
        path:'',component:ComponentPaiDeTodos,
        canActivate:[MetodoGuardDaSuaAplicaçãoQueValidaOToken],
            children:[ -> quer dizer que as rotas a seguir dependem da rota pai
                {path:'nomeQueQuerParaSuaRotaFilha',component:HomeComponent},
                {path:'nomeQueQuerParaSuaRotaFilha',component:TechnicalComponent},
                {path:'nomeQueQuerParaSuaRotaFilha/rotaQueOUsuarioSeraRedirecionadaAPartirDaRotaFila',component:TecnicoCreateComponent},
                {path:'nomeQueQuerParaSuaRotaFilha/rotaQueOUsuarioSeraRedirecionadaAPartirDaRotaFila/:nomeDeUmValorQueSeraPassadoNoLink(funciona como um local storage)',component:TecnicoCreateComponent}
            ]
        }

routerLink : funciona como uma ancora do html

    routerLink="/" redireciona para a pagina denominada principal no caso o path:''

    routerLink="../" volta na pagina anterior que o usuario estava, se adicionar ../../ volta duas ../../../ tres e assim vai

    routerLink="nomeDaRota" caso o usuario click então sera redirecionado para o component especificado na rota ({path:'nomeDaRota',component:HomeComponent},) mesmo se ela for filha de uma rota principal

    routerLink="nomeDaRota/{{valor}}" caso o usuario click então sera redirecionado para o component especificado na rota ({path:'nomeDaRota/:valor',component:HomeComponent},) mesmo se ela for filha de uma rota principal, assim ela vai para a rota e leva o valor junto

this.router.navigate : funciona como um link de redirecionamento

    this.router.navigate(['/']) redireciona para a pagina denominada principal no caso o path:''

    this.router.navigate(['nomeDaRota']) sera redirecionado para o component especificado na rota ({path:'nomeDaRota',component:HomeComponent},) mesmo se ela for filha de uma rota principal, as caso sua rota pai não esteja vazia e voce quer carregar essa pagina mas ela não vai então tente colocar 'nomeDaRotaPai/nomeDaRotaQueVoceQuer'

    this.router.navigate(['nomeDaRota/rotaQueOUsuarioSeraRedirecionadaAPartirDaMesma']) esse é caso nos routing voce tenha especificado um path da seguinte maneira {path:'nomeDaRota/rotaQueOUsuarioSeraRedirecionadaAPartirDaMesma',component:TecnicoCreateComponent},

class="material-icons edit"

    algumas classes no html podem ter essas propriedades, muitas vezes a primeira parte é bootstrap ou do framework e a segunda e do css, por isso 

pegar valor que esta junto a rota

    primeiro coloque no constructor private activatedRouter:ActivatedRoute

    variavel = this.activatedRouter.snapshot.paramMap.get('nomeDoValorQueVoceColocouNoPath'); {path:'nomeQueQuerParaSuaRotaFilha/rotaQueOUsuarioSeraRedirecionadaAPartirDaRotaFila/:nomeDoValorQueVoceColocouNoPath
-------------------------------------------------------------------------------------------------------------------------------------

Codigos BACK

    quando voce faz um lista.stream().map(x -> new Classe(objeto)).collect(Collector.toList) voce esta fazendo uma lista de uma nova classe DTO

    quando voce faz um lista.stream().map(x -> Classe.metodo(x)).collect(Collector.toSet) voce esta fazendo uma conversão especifica que voce quer do Objeto atual

Comandos BACK

gerando jar do back
    mvn clean install
    dir
    cd
    java -jar nomeDoJarGerado-versaoApresentada-SNAPSHOT.jar exemplo (amazonApi-0.0.1-SNAPSHOT.jar)
 
