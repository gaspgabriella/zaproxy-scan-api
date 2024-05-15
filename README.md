# Zaproxy Scan Api

Não consegui continuar a API a partir do scan pois o scan não finaliza na minha máquina por conta de ser um processo extremamente pesado, não sobrou tempo para esperar completar este scan que as vezes empacava em 38% e demorava muito.

Porém irei citar as estratégias que usaria para persistir os resultados e realizar todo este scan.

## Problemas com o Active Scan

Tive alguns problemas de cara ao tentar fazer o scaneamento direto com o Active Scan por conta da URL não estar listada na SubTree. Portanto a solução que deixava eu realizar este scan foi adicionar o localserver na api de network do zaproxy, realizar um scan utilizando o spider e após isto realizar o scan utilizando o Active Scan.

## Resultados do scan e persistencia no banco de dados

Realizei o esqueleto da tabela que seria de results dos scans do active scan no banco de dados, para trazer os dados de resultado do active scan eu teria que aguardar completar e não poderia ocupar a máquina da API até que este scan fosse concluido então poderia fazer de dois jeitos esse save dos resultados 

- Realizar um Cron diário para salvar todos os alertas novos de vulnerabilidades apontados no endpoint de alerts.
- Realizar uma fila de espera para cada scan, assim eu realizaria a checagem se o processo foi concluido (A cada 1 hora por exemplo) e assim que concluido limparia a fila de scans. Creio que este seja o melhor caminho pois não precisaria fazer o sync de alert diário e só quando necessario. Para realizar esta queue poderiamos utilizar o Redis para orquestrar isto e ir tirando da fila quando o scan fosse finalizado em status de 100%.

O mundo ideal seria se o zaproxy tivesse um webhook para quando o scan fosse concluido notificasse nossa API, assim salvando os resultados do scan; Porém não encontrei documentação que apontasse que esta feature existe;

## Alterações feitas no docker-compose

Percebi que o docker-compose contava com uma imagem do zaproxy que não existia, portanto troquei-a para conseguir rodar as aplicações.
