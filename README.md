O projeto consiste em criar um sistema embarcado para coletar informações de um veiculo, salvar
em uma base dados local e após esses dados serem trabalhados em uma aplicação Alto Nível.
O dispositivo escolhido para esse projeto foi o Raspberry Pi, essa plataforma possui todas as características de um computador convencional, possuindo opção de instalação de sistema operacional, placa WiFi, Bluetooth, entradas USB e GPIOs para manipulação de sensores e atuadores. Além do hardware que compõe a placa, o seu custo também foi levado em consideração para esse projeto. 

Após a definição do projeto, foi iniciado a fase de testes e de pesquisa, em um primeiro momento foi aplicado testes utilizando o framework QtCreator, para construir uma aplicação onde pude-se buscar o Bluetooth e que lista-se os dispositivos para parear. Também foi instalado o sistemas operacional Raspbian sem interface gráfica, porém utilizando o servidor xorg. Foi criado algumas tentativas de conseguir desenvolver a aplicação, porém o custo de aprendizagem era alto de mais e foi realizado uma nova pesquisa para buscar uma solução de desenvolvimento nessa área.
Com essa nova pesquisa, foi possível encontrar diversas aplicações com o uso do Raspberry Pi na área de coletas de dados, como o sistema AutoPi, que possui um dispositivo que fica conectado ao veículo e envia informações para um sistema em nuvem, que pode ser acessado de qualquer lugar do mundo. Então foi utilizado um projeto de open-source, em linguagem python, para realizar essa tarefa.
**Desenvolvimento Inicial do Projeto**
1 - Instalado o sistema operacional Raspbian, com interface gráfica
2 - Instalado o módulo de desenvolvimento do bluetooth, o bluez e demais bibliotecas
3 - Instalado via pip a biblioteca obd, mais conhecida como pyobd
4 - Realizado testes com essas libs, constatado funcionamento correto, pois ficava buscando o ELM237
5 - Adquirir o adaptador ELM237
6 - Após a aquisição, realizar testes para buscar as informações através da aplicação que vai ser desenvolvida e será baseada em um projeto Alemão, o PYOBD. Link do site >> http://www.obdtester.com/pyobd
7 - Será realizado a instalação do mysql
8 - Será desenvolvido um script para salvar os dados em uma base de dados, ou seja, os dados serão salvos conforme a sua coleta e após poderão ser trabalhados.
9 - Quando iniciar o raspberry a aplicação deverá iniciar automaticamente
No primeiro momento essa aplicação será apenas para coletar informações e salvar em uma base de dados instalada no próprio Raspberry.
Foi instalado a versão xubuntu no Raspberry Pi, e adaptado o projeto PyOBD para uma versão que salve em uma base de dados MySQL, atualmente o projeto coleta as informações que são recebidas do OBD2 e salva as mesmas em um arquivo de log. Com essa melhoria será possível enviar esses dados registrados para qualquer outro servidor de banco de dados.
Foi criado uma base de dados denominada de car_system com uma unica tabela denominada de logs, nessa tabela inicialmente foram definidos os atributos id, rpm (rotações por minuto), temperatura do motor e velocidade (Km). O sistema de banco de dados está instalado na própria plataforma.
Após a instalação e a base de dados estar executando corretamente, foi adaptado o projeto PyOBD para iniciar quando a plataforma iniciar, para isso foi definido um procedimento na crontab do Linux para iniciar o colector e a interface IHM, onde irá salvar os dados na base de dados, através de um script em python que realiza uma comunicação com o banco de dados e insere esses dados na tabela. Para isso foi utilizado uma lib MySQL for Python. Os dados simulados atualmente estão statics, pois só será possível realizar o teste final quando o ELM237 chegar.
