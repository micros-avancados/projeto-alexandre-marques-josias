**Documentação de Sistema Embarcado**

O projeto consiste em criar um sistema embarcado para coletar informações de um veiculo, salvar
em uma base dados local e após esses dados serem trabalhados em uma aplicação Alto Nível.
O dispositivo escolhido para esse projeto foi o Raspberry Pi, essa plataforma possui todas as características de um computador convencional, possuindo opção de instalação de sistema operacional, placa WiFi, Bluetooth, entradas USB e GPIOs para manipulação de sensores e atuadores. Além do hardware que compõe a placa, o seu custo também foi levado em consideração para esse projeto. 

Após a definição do projeto, foi iniciado a fase de testes e de pesquisa, em um primeiro momento foi aplicado testes utilizando o framework QtCreator C++, para construir uma aplicação que realiza-se a busca de dispositivos via Bluetooth para parear. Também foi instalado o sistema operacional Raspbian sem interface gráfica, porém utilizando o servidor xorg. Foi criado algumas tentativas de conseguir desenvolver a aplicação, porém o custo de tempo era alto de mais e foi realizado uma nova pesquisa para buscar uma solução de desenvolvimento nessa área. Com essa nova pesquisa, foi possível encontrar diversas aplicações com o uso do Raspberry Pi na área de coletas de dados usando OBD2, como o sistema AutoPi, que possui um dispositivo que fica conectado ao veículo e envia informações para um sistema em nuvem, que pode ser acessado de qualquer lugar do mundo. Então foi utilizado um projeto open-source, em linguagem python, para realizar essa tarefa.

**Desenvolvimento Inicial do Projeto**

1 - Instalado o sistema operacional Raspbian, com interface gráfica

2 - Instalado o módulo de desenvolvimento do bluetooth, o bluez e demais bibliotecas

3 - Instalado via pip a biblioteca obd, mais conhecida como pyobd

4 - Realizado testes com essas libs, constatado funcionamento correto, pois ficava buscando o ELM237

5 - Adquirir o adaptador ELM237

6 - Após a aquisição, realizar testes para buscar as informações através da aplicação que vai ser desenvolvida e será baseada em um projeto Alemão, o PYOBD.

7 - Será realizado a instalação do mysql

8 - Será desenvolvido um script para salvar os dados em uma base de dados, ou seja, os dados serão salvos conforme a sua coleta e após poderão ser trabalhados.

9 - Quando iniciar o Raspberry Pi a aplicação deverá iniciar automaticamente.

Em um primeiro momento essa aplicação será apenas para coletar informações e salvar em uma base de dados instalada no próprio Raspberry.
Foi instalado a versão xubuntu no Raspberry Pi, e adaptado o projeto PyOBD para uma versão que salve em uma base de dados MySQL, atualmente o projeto coleta as informações que são recebidas do OBD2 e salva as mesmas em um arquivo de log. Com essa melhoria será possível enviar esses dados registrados para qualquer outro servidor de banco de dados.
Foi criado uma base de dados denominada de car_system com uma unica tabela denominada de logs, nessa tabela inicialmente foram definidos os atributos id, rpm (rotações por minuto), temperatura do motor e velocidade (Km). O sistema de banco de dados está instalado na própria plataforma. Após a instalação e a base de dados estar executando corretamente, foi adaptado o projeto PyOBD para iniciar quando a plataforma iniciar, para isso foi definido um procedimento na crontab do Linux para iniciar o colector e a interface IHM, onde irá salvar os dados na base de dados MySQL, através de um script em python que realiza uma comunicação com o banco de dados e insere esses dados na tabela. Para isso foi utilizado uma lib MySQL for Python. Os dados simulados atualmente estão statics, pois só será possível realizar o teste final quando o ELM237 chegar.

Após a aquisição do dispositivo ELM237, foi possível realizar alguns testes utilizando a aplicação. Como o pareamento e a exibição desses dados em um display, utilzando o sistema desenvolvido para o Raspberry Pi. O projeto está praticamente pronto, a necessidade de realizar alguns ajustes na parte de salvar os dados e realizar a inicialização do script automaticamente.

**Etapa Final do Projeto**
Após diversos testes e análises, foi reavaliado o desenvolvimento do sistema, mantendo o objetivo principal, de criar um sistema para
coletar as informações do veículo via comunicação OBD. Foi continuado o uso do Raspberry Pi, o sistema operacional Linux Xubuntu 16.04, e instalado os módulos python e bluetooth.

Foi realizada uma instalação nova do sistema operacional Xubuntu e realizado todas as configurações do zero, com isso foi possível atualizar os repositorios novamente e executar os scripts de desenvolvimento em python. Para ocorrer a coleta das informações dos veículos de forma correta foi seguido uma instalação de forma procedural, de alguns scripts.

1 - sudo apt-get install python-wxtools

2 - sudo apt-get install bluetooth bluez blueman

3 - Reiniciado o dispositivo

Todos esses procedimento listados acima, foram realizados em um dispositivo igual ao da Figura 1.

**Figura 1 - Raspberry Utilizado**
![pi](https://user-images.githubusercontent.com/22698776/48963682-1ced5100-ef7f-11e8-9919-a4effd1f010c.jpg)

O módulo OBD2, foi plugado em 3 modelos de carros diferentes para realização de testes de pareamento, cada veículo possui
o conector OBD em lugares diferentes, os carros que foram possíveis realizar os testes iniciais foram, Clio da Renault, Vitara da
Suzuki e Fiesta da Ford, ambos reconheceram o dispositivo perfeitamente e realizaram a coleta de dados através de um script em python.
A Figura 2 representa o modelo de OBD2, ESP237, utilizado para o experimento.

**Figura 2 - Módulo OBD2**
![obd2](https://user-images.githubusercontent.com/22698776/48963783-7a829d00-ef81-11e8-941e-6d9cc6ed6c61.jpg)

Como se trata de um sistema embarcado, deve realizar uma interação com o usuário, exibindo informações sobre o veículo, para isso
foi criado um software que exibe os dados que foram inseridos na base de dados pelo script python, e esses registros, são informações
coletadas pelo adaptador OBD2, que realiza uma leitura dos sensores do veículo. A Figura 3 exibe a interface principal onde os dados são
exibidos em uma tabela e ao selecionar uma linha são replicados nos dashboards quadrados acima dela. O sistema foi baseado em requisitos
de software para IHM, ou seja a interface gráfica é precaria.

**Figura 3 - Software de Monitoramento IHM**
![ft-1](https://user-images.githubusercontent.com/22698776/49408328-f5e30c00-f742-11e8-822e-ea5583964003.png)

Na Figura 4 é possivel ver um gráfico que mostra os valores calculados que estão salvos na base de dados.

**Figura 4 - Tela de Gráficos do Sistema**
![ft3](https://user-images.githubusercontent.com/22698776/49408349-04c9be80-f743-11e8-9ec2-5ed9f698d60b.png)

Na Figura 5 é exibido a tela de alarmes, onde será possível ver as falhas do sistema, existe uma média que foi definida
para mostrar a célula da tabela quando determinado valor de RPM ou Aceleração estiver fora do comum.

**Figura 5 - Tela de Alarmes**
![ft2](https://user-images.githubusercontent.com/22698776/49408353-0c896300-f743-11e8-889c-93df0e07fbdd.png)

Todos esses dados são salvos em uma base de dados que fica alocada no próprio Raspberry Pi, o sistema de banco de dados instalado
foi o MySQL Server, onde foi criado uma base de dados de codenome log_system. A Figura 6 exibe a modelagem da tabela desse banco de 
dados.

**Figura 6 - Modelagem do Banco de Dados**


![model](https://user-images.githubusercontent.com/22698776/48963869-c33b5580-ef83-11e8-8731-1f3454f1420a.png)

Para que esse projeto pude-se ser feito, foi utilizado as seguintes linguagens de programção: Java e Python.

Podemos concluir que o projeto funcionou corretamente, foi realizado formatação dos valores e ajuste no gráfico. 

**Obs: A inicialização de scripts em python para coleta e aplicação gráfica, executam na inicialização do sistema operacional
para isso ser possível, ambas foram adicionadas em aplicações de sessão do linux. A inserção de registros também ocorre de forma
automatíca e a busca é controlada por uma Thread, que fica atualizando de 1 em 1 segundo os dados.**
