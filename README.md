# Inventory Simulation Project
### Gautam Nair

## Menu

| Command              | Function                      | Syntax                          |  
|----------------------|-------------------------------|---------------------------------|
| reset                | reset inventory to empty	     | 	reset                          |
| exit                 | 	 exit CLI app                | 	     exit                      |
| transferin / in	     | 	 add items                   | in "sku" "qty" "price per unit" |
| transferout / out    | 	  remove items               | out "sku" "qty"                 |
| listrules / lr       | 	get list of active rules     | 	listrules  / lr                |
| getrules             | 	 get supported rules         | 	getrules                       |
| addrule              | 	    add a rule               | 	addrule allowpartial Y         |
| removerule           | 	   remove a rule             | 	removerule allowpartial        |
| listtransfers / lt   | 	   list past transfers       | 	listtransfers / lt             |
| snapshot / s         | 	      get current inventory  | 	 snapshot / s                  |
| gettransasctions /gt | 	get past transcation details | 	 gettransasctions / gt         |