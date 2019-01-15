
### 利用web3j将solidity官方文档“投票合约”部署到区块链，并且完成功能调度和投票功能

_.sol  .bin   .abi  文件均在solidity文件夹下_

- step 1: install docker for your computer
- step 2: run command in shell or powershell
```
docker run -d --name ethereum -p 8545:8545 -p 30303:30303 ethereum/client-go --rpc --rpcaddr "0.0.0.0" --rpcapi="db,eth,net,web3,personal" --rpccorsdomain "*" --dev
```
check ethereum if it's running
```
docker ps
```
or start ethereum
```
docker start ethereum
```
- step 3: improt this project as maven project
- step 4: run EthereumDemoApplication As Spring-Boot Application
- step 5: open your chrome 
```
localhost:8080/swagger-ui.html
```
- step 6: enjoy swagger to call (deploy first then vote and get winner )
- step 7: you can complete all to-do part by yourself 


### If you like please give me a star!

## thank you!