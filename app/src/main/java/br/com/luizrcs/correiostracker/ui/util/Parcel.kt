package br.com.luizrcs.correiostracker.ui.util

import br.com.luizrcs.correiostracker.repository.*
import com.github.salomonbrys.kotson.*
import com.google.gson.*

fun generateTestParcels(): List<Parcel> {
	val gson = Gson()
	val response =
		"""{"versao":"3.0","quantidade":"9","pesquisa":"Lista de Objetos","resultado":"Todos os eventos","objeto":[{"numero":"LE396038958SE","sigla":"LE","nome":"OBJETO INTERNACIONAL PRIME","categoria":"PRIME IMPORTAÇÃO","evento":[{"tipo":"BDE","status":"01","data":"04/10/2021","hora":"16:00","criacao":"04102021160033","descricao":"Objeto entregue ao destinatário","recebedor":{"nome":"?","documento":"?","comentario":"?"},"unidade":{"local":"CDD VILA PIRES","codigo":"09110972","cidade":"SANTO ANDRE","uf":"SP","sto":"00025432","tipounidade":"Unidade de Distribuição","endereco":{"codigo":"34565","cep":"09015972","logradouro":"AVENIDA ARTUR DE QUEIROS","numero":"961","localidade":"SANTO ANDRE","uf":"SP","bairro":"CASA BRANCA","latitude":"-23","longitude":"-46"}},"cepDestino":"0000000","prazoGuarda":"0","diasUteis":"0","dataPostagem":"24/09/2021"},{"tipo":"OEC","status":"01","data":"04/10/2021","hora":"10:46","criacao":"04102021104642","descricao":"Objeto saiu para entrega ao destinatário","unidade":{"local":"CDD VILA PIRES","codigo":"09110972","cidade":"SANTO ANDRE","uf":"SP","sto":"00025432","tipounidade":"Unidade de Distribuição","endereco":{"codigo":"34565","cep":"09015972","logradouro":"AVENIDA ARTUR DE QUEIROS","numero":"961","localidade":"SANTO ANDRE","uf":"SP","bairro":"CASA BRANCA","latitude":"-23","longitude":"-46"}},"cepDestino":"0000000","prazoGuarda":"0","diasUteis":"0","dataPostagem":"24/09/2021","detalheOEC":{"carteiro":"89286723","distrito":"305","lista":"111100018347","unidade":"09110972"}},{"tipo":"DO","status":"01","data":"01/10/2021","hora":"10:43","criacao":"01102021104347","descricao":"Objeto em trânsito - por favor aguarde","unidade":{"local":"CTE SAUDE","codigo":"04293970","cidade":"SAO PAULO","uf":"SP","sto":"00035712","tipounidade":"Unidade de Tratamento","endereco":{"codigo":"41919","cep":"04293970","logradouro":"RUA DO BOQUEIRAO","numero":"320","localidade":"SAO PAULO","uf":"SP","bairro":"SAUDE","latitude":"-23.6215902","longitude":"-46.6171872"}},"destino":[{"local":"CDD VILA PIRES","codigo":"09110972","cidade":"SANTO ANDRE","bairro":"CASA BRANCA","uf":"SP","endereco":{"codigo":"34565","cep":"09015972","logradouro":"AVENIDA ARTUR DE QUEIROS","numero":"961","localidade":"SANTO ANDRE","uf":"SP","bairro":"CASA BRANCA","latitude":"-23","longitude":"-46"}}],"cepDestino":"0000000","prazoGuarda":"0","diasUteis":"0","dataPostagem":"24/09/2021"},{"tipo":"RO","status":"01","data":"29/09/2021","hora":"14:31","criacao":"29092021143122","descricao":"Objeto em trânsito - por favor aguarde","unidade":{"local":"UNIDADE INTERNACIONAL CURITIBA","codigo":"80235980","cidade":"CURITIBA","uf":"PR","sto":"00423791","tipounidade":"Unidade Operacional","endereco":{"codigo":"192950","cep":"83330110","logradouro":"UNIDADE INTERNACIONAL PINHAIS","localidade":"CURITIBA","uf":"PR","bairro":"PINHAIS","latitude":"-25","longitude":"-49"}},"destino":[{"local":"CTE SAUDE","codigo":"04293970","cidade":"SAO PAULO","bairro":"SAUDE","uf":"SP","endereco":{"codigo":"41919","cep":"04293970","logradouro":"RUA DO BOQUEIRAO","numero":"320","localidade":"SAO PAULO","uf":"SP","bairro":"SAUDE","latitude":"-23.6215902","longitude":"-46.6171872"}}],"cepDestino":"0000000","prazoGuarda":"0","diasUteis":"0","dataPostagem":"24/09/2021"},{"tipo":"PAR","status":"10","data":"29/09/2021","hora":"14:29","criacao":"29092021142922","descricao":"Fiscalização aduaneira finalizada","unidade":{"local":"UNIDADE INTERNACIONAL CURITIBA","codigo":"80235980","cidade":"CURITIBA","uf":"PR","sto":"00423791","tipounidade":"Unidade Operacional","endereco":{"codigo":"192950","cep":"83330110","logradouro":"UNIDADE INTERNACIONAL PINHAIS","localidade":"CURITIBA","uf":"PR","bairro":"PINHAIS","latitude":"-25","longitude":"-49"}},"cepDestino":"0000000","prazoGuarda":"0","diasUteis":"0","dataPostagem":"24/09/2021"},{"tipo":"PAR","status":"16","data":"28/09/2021","hora":"19:55","criacao":"28092021195513","descricao":"Objeto recebido pelos Correios do Brasil","unidade":{"local":"UNIDADE INTERNACIONAL CURITIBA","codigo":"80235980","cidade":"CURITIBA","uf":"PR","sto":"00423791","tipounidade":"Unidade Operacional","endereco":{"codigo":"192950","cep":"83330110","logradouro":"UNIDADE INTERNACIONAL PINHAIS","localidade":"CURITIBA","uf":"PR","bairro":"PINHAIS","latitude":"-25","longitude":"-49"}},"cepDestino":"0000000","prazoGuarda":"0","diasUteis":"0","dataPostagem":"24/09/2021"},{"tipo":"RO","status":"01","data":"24/09/2021","hora":"15:34","criacao":"24092021153400","descricao":"Objeto em trânsito - por favor aguarde","unidade":{"local":"SUECIA","codigo":"00752000","sto":"99999999","tipounidade":"País","endereco":{"codigo":"37862","latitude":"60.12816100000001","longitude":"18.643501"}},"destino":[{"local":"Unidade de Tratamento Internacional","codigo":"00500001","uf":"BR","endereco":{"codigo":"77159","uf":"BR","latitude":"-14.235004","longitude":"-51.92528"}}],"cepDestino":"0000000","prazoGuarda":"0","diasUteis":"0","dataPostagem":"24/09/2021"}]},{"numero":"NX383102683BR","sigla":"NX","nome":"CORREIOS PACKET STANDARD","categoria":"PACKET STANDARD IMPORTAÇÃO","evento":[{"tipo":"BDE","status":"01","data":"21/09/2021","hora":"16:02","criacao":"21092021160251","descricao":"Objeto entregue ao destinatário","recebedor":{},"unidade":{"local":"CDD VILA PIRES","codigo":"09110972","cidade":"SANTO ANDRE","uf":"SP","sto":"00025432","tipounidade":"Unidade de Distribuição","endereco":{"codigo":"34565","cep":"09015972","logradouro":"AVENIDA ARTUR DE QUEIROS","numero":"961","localidade":"SANTO ANDRE","uf":"SP","bairro":"CASA BRANCA","latitude":"-23","longitude":"-46"}},"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"12/09/2021"},{"tipo":"OEC","status":"01","data":"21/09/2021","hora":"12:30","criacao":"21092021123037","descricao":"Objeto saiu para entrega ao destinatário","unidade":{"local":"CDD VILA PIRES","codigo":"09110972","cidade":"SANTO ANDRE","uf":"SP","sto":"00025432","tipounidade":"Unidade de Distribuição","endereco":{"codigo":"34565","cep":"09015972","logradouro":"AVENIDA ARTUR DE QUEIROS","numero":"961","localidade":"SANTO ANDRE","uf":"SP","bairro":"CASA BRANCA","latitude":"-23","longitude":"-46"}},"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"12/09/2021","detalheOEC":{"carteiro":"81094817","distrito":"605","lista":"115100017678","unidade":"09110972"}},{"tipo":"RO","status":"01","data":"18/09/2021","hora":"06:00","criacao":"18092021060004","descricao":"Objeto em trânsito - por favor aguarde","unidade":{"local":"CTE SAUDE","codigo":"04293970","cidade":"SAO PAULO","uf":"SP","sto":"00035712","tipounidade":"Unidade de Tratamento","endereco":{"codigo":"41919","cep":"04293970","logradouro":"RUA DO BOQUEIRAO","numero":"320","localidade":"SAO PAULO","uf":"SP","bairro":"SAUDE","latitude":"-23.6215902","longitude":"-46.6171872"}},"destino":[{"local":"CDD VILA PIRES","codigo":"09110972","cidade":"SANTO ANDRE","bairro":"CASA BRANCA","uf":"SP","endereco":{"codigo":"34565","cep":"09015972","logradouro":"AVENIDA ARTUR DE QUEIROS","numero":"961","localidade":"SANTO ANDRE","uf":"SP","bairro":"CASA BRANCA","latitude":"-23","longitude":"-46"}}],"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"12/09/2021"},{"tipo":"RO","status":"01","data":"16/09/2021","hora":"07:46","criacao":"16092021074619","descricao":"Objeto em trânsito - por favor aguarde","unidade":{"local":"CENTRO INTERNACIONAL PR","codigo":"81010971","cidade":"CURITIBA","uf":"PR","sto":"00423791","tipounidade":"Unidade de Logística Integrada","endereco":{"codigo":"296118","cep":"83330110","logradouro":"RUA SALGADO FILHO","numero":"476","localidade":"CURITIBA","uf":"PR","bairro":"CURITIBA","latitude":"-25","longitude":"-49"}},"destino":[{"local":"CTE SAUDE","codigo":"04293970","cidade":"SAO PAULO","bairro":"SAUDE","uf":"SP","endereco":{"codigo":"41919","cep":"04293970","logradouro":"RUA DO BOQUEIRAO","numero":"320","localidade":"SAO PAULO","uf":"SP","bairro":"SAUDE","latitude":"-23.6215902","longitude":"-46.6171872"}}],"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"12/09/2021"},{"tipo":"PAR","status":"10","data":"16/09/2021","hora":"07:21","criacao":"16092021072112","descricao":"Fiscalização aduaneira finalizada","unidade":{"local":"UNIDADE INTERNACIONAL CURITIBA","codigo":"80235980","cidade":"CURITIBA","uf":"PR","sto":"00423791","tipounidade":"Unidade Operacional","endereco":{"codigo":"192950","cep":"83330110","logradouro":"UNIDADE INTERNACIONAL PINHAIS","localidade":"CURITIBA","uf":"PR","bairro":"PINHAIS","latitude":"-25","longitude":"-49"}},"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"12/09/2021"},{"tipo":"PAR","status":"16","data":"14/09/2021","hora":"13:45","criacao":"14092021134515","descricao":"Objeto recebido pelos Correios do Brasil","unidade":{"local":"CENTRO INTERNACIONAL PR","codigo":"81010971","cidade":"CURITIBA","uf":"PR","sto":"00423791","tipounidade":"Unidade de Logística Integrada","endereco":{"codigo":"296118","cep":"83330110","logradouro":"RUA SALGADO FILHO","numero":"476","localidade":"CURITIBA","uf":"PR","bairro":"CURITIBA","latitude":"-25","longitude":"-49"}},"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"12/09/2021"},{"tipo":"PO","status":"01","data":"12/09/2021","hora":"08:44","criacao":"12092021084417","descricao":"Objeto postado","unidade":{"local":"CHINA","codigo":"00156000","sto":"99999999","tipounidade":"País","endereco":{"codigo":"39695","latitude":"35.86166","longitude":"104.195397"}},"postagem":{"cepdestino":"44640000","ar":"N","mp":"N","dh":"N","peso":"0","volume":"0","dataprogramada":"01/01/1900 00:00:00","datapostagem":"01/01/1900 00:00:00","prazotratamento":"0"},"cepDestino":"44640000","prazoGuarda":"0","diasUteis":"0","dataPostagem":"12/09/2021"}]},{"numero":"LE380746619SE","sigla":"LE","nome":"OBJETO INTERNACIONAL PRIME","categoria":"PRIME IMPORTAÇÃO","evento":[{"tipo":"BDE","status":"01","data":"23/09/2021","hora":"16:55","criacao":"23092021165537","descricao":"Objeto entregue ao destinatário","recebedor":{"nome":"?","documento":"?","comentario":"?"},"unidade":{"local":"CDD VILA PIRES","codigo":"09110972","cidade":"SANTO ANDRE","uf":"SP","sto":"00025432","tipounidade":"Unidade de Distribuição","endereco":{"codigo":"34565","cep":"09015972","logradouro":"AVENIDA ARTUR DE QUEIROS","numero":"961","localidade":"SANTO ANDRE","uf":"SP","bairro":"CASA BRANCA","latitude":"-23","longitude":"-46"}},"cepDestino":"0000000","prazoGuarda":"0","diasUteis":"0","dataPostagem":"11/09/2021"},{"tipo":"OEC","status":"01","data":"23/09/2021","hora":"11:07","criacao":"23092021110726","descricao":"Objeto saiu para entrega ao destinatário","unidade":{"local":"CDD VILA PIRES","codigo":"09110972","cidade":"SANTO ANDRE","uf":"SP","sto":"00025432","tipounidade":"Unidade de Distribuição","endereco":{"codigo":"34565","cep":"09015972","logradouro":"AVENIDA ARTUR DE QUEIROS","numero":"961","localidade":"SANTO ANDRE","uf":"SP","bairro":"CASA BRANCA","latitude":"-23","longitude":"-46"}},"cepDestino":"0000000","prazoGuarda":"0","diasUteis":"0","dataPostagem":"11/09/2021","detalheOEC":{"carteiro":"81094817","distrito":"605","lista":"124100017825","unidade":"09110972"}},{"tipo":"RO","status":"01","data":"22/09/2021","hora":"14:42","criacao":"22092021144241","descricao":"Objeto em trânsito - por favor aguarde","unidade":{"local":"CTE SAUDE","codigo":"04293970","cidade":"SAO PAULO","uf":"SP","sto":"00035712","tipounidade":"Unidade de Tratamento","endereco":{"codigo":"41919","cep":"04293970","logradouro":"RUA DO BOQUEIRAO","numero":"320","localidade":"SAO PAULO","uf":"SP","bairro":"SAUDE","latitude":"-23.6215902","longitude":"-46.6171872"}},"destino":[{"local":"CDD VILA PIRES","codigo":"09110972","cidade":"SANTO ANDRE","bairro":"CASA BRANCA","uf":"SP","endereco":{"codigo":"34565","cep":"09015972","logradouro":"AVENIDA ARTUR DE QUEIROS","numero":"961","localidade":"SANTO ANDRE","uf":"SP","bairro":"CASA BRANCA","latitude":"-23","longitude":"-46"}}],"cepDestino":"0000000","prazoGuarda":"0","diasUteis":"0","dataPostagem":"11/09/2021"},{"tipo":"RO","status":"01","data":"21/09/2021","hora":"13:31","criacao":"21092021133127","descricao":"Objeto em trânsito - por favor aguarde","unidade":{"local":"UNIDADE INTERNACIONAL CURITIBA","codigo":"80235980","cidade":"CURITIBA","uf":"PR","sto":"00423791","tipounidade":"Unidade Operacional","endereco":{"codigo":"192950","cep":"83330110","logradouro":"UNIDADE INTERNACIONAL PINHAIS","localidade":"CURITIBA","uf":"PR","bairro":"PINHAIS","latitude":"-25","longitude":"-49"}},"destino":[{"local":"CTE SAUDE","codigo":"04293970","cidade":"SAO PAULO","bairro":"SAUDE","uf":"SP","endereco":{"codigo":"41919","cep":"04293970","logradouro":"RUA DO BOQUEIRAO","numero":"320","localidade":"SAO PAULO","uf":"SP","bairro":"SAUDE","latitude":"-23.6215902","longitude":"-46.6171872"}}],"cepDestino":"0000000","prazoGuarda":"0","diasUteis":"0","dataPostagem":"11/09/2021"},{"tipo":"PAR","status":"10","data":"21/09/2021","hora":"13:29","criacao":"21092021132927","descricao":"Fiscalização aduaneira finalizada","unidade":{"local":"UNIDADE INTERNACIONAL CURITIBA","codigo":"80235980","cidade":"CURITIBA","uf":"PR","sto":"00423791","tipounidade":"Unidade Operacional","endereco":{"codigo":"192950","cep":"83330110","logradouro":"UNIDADE INTERNACIONAL PINHAIS","localidade":"CURITIBA","uf":"PR","bairro":"PINHAIS","latitude":"-25","longitude":"-49"}},"cepDestino":"0000000","prazoGuarda":"0","diasUteis":"0","dataPostagem":"11/09/2021"},{"tipo":"PAR","status":"16","data":"16/09/2021","hora":"15:56","criacao":"16092021155636","descricao":"Objeto recebido pelos Correios do Brasil","unidade":{"local":"UNIDADE INTERNACIONAL CURITIBA","codigo":"80235980","cidade":"CURITIBA","uf":"PR","sto":"00423791","tipounidade":"Unidade Operacional","endereco":{"codigo":"192950","cep":"83330110","logradouro":"UNIDADE INTERNACIONAL PINHAIS","localidade":"CURITIBA","uf":"PR","bairro":"PINHAIS","latitude":"-25","longitude":"-49"}},"cepDestino":"0000000","prazoGuarda":"0","diasUteis":"0","dataPostagem":"11/09/2021"},{"tipo":"RO","status":"01","data":"11/09/2021","hora":"18:19","criacao":"11092021181900","descricao":"Objeto em trânsito - por favor aguarde","unidade":{"local":"SUECIA","codigo":"00752000","sto":"99999999","tipounidade":"País","endereco":{"codigo":"37862","latitude":"60.12816100000001","longitude":"18.643501"}},"destino":[{"local":"Unidade de Tratamento Internacional","codigo":"00500001","uf":"BR","endereco":{"codigo":"77159","uf":"BR","latitude":"-14.235004","longitude":"-51.92528"}}],"cepDestino":"0000000","prazoGuarda":"0","diasUteis":"0","dataPostagem":"11/09/2021"}]},{"numero":"OQ122873921BR","sigla":"OQ","nome":"ETIQUETA LOGICA SEDEX OQ","categoria":"SEDEX","evento":[{"tipo":"BDE","status":"01","data":"16/09/2021","hora":"19:58","criacao":"16092021195819","descricao":"Objeto entregue ao destinatário","recebedor":{},"unidade":{"local":"CDD VILA PIRES","codigo":"09110972","cidade":"SANTO ANDRE","uf":"SP","sto":"00025432","tipounidade":"Unidade de Distribuição","endereco":{"codigo":"34565","cep":"09015972","logradouro":"AVENIDA ARTUR DE QUEIROS","numero":"961","localidade":"SANTO ANDRE","uf":"SP","bairro":"CASA BRANCA","latitude":"-23","longitude":"-46"}},"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"15/09/2021"},{"tipo":"OEC","status":"01","data":"16/09/2021","hora":"14:04","criacao":"16092021140422","descricao":"Objeto saiu para entrega ao destinatário","unidade":{"local":"CDD VILA PIRES","codigo":"09110972","cidade":"SANTO ANDRE","uf":"SP","sto":"00025432","tipounidade":"Unidade de Distribuição","endereco":{"codigo":"34565","cep":"09015972","logradouro":"AVENIDA ARTUR DE QUEIROS","numero":"961","localidade":"SANTO ANDRE","uf":"SP","bairro":"CASA BRANCA","latitude":"-23","longitude":"-46"}},"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"15/09/2021","detalheOEC":{"carteiro":"89171969","distrito":"704","lista":"115100017593","unidade":"09110972"}},{"tipo":"RO","status":"01","data":"16/09/2021","hora":"02:51","criacao":"16092021025152","descricao":"Objeto em trânsito - por favor aguarde","unidade":{"local":"CTE SAUDE","codigo":"04293970","cidade":"SAO PAULO","uf":"SP","sto":"00035712","tipounidade":"Unidade de Tratamento","endereco":{"codigo":"41919","cep":"04293970","logradouro":"RUA DO BOQUEIRAO","numero":"320","localidade":"SAO PAULO","uf":"SP","bairro":"SAUDE","latitude":"-23.6215902","longitude":"-46.6171872"}},"destino":[{"local":"CDD VILA PIRES","codigo":"09110972","cidade":"SANTO ANDRE","bairro":"CASA BRANCA","uf":"SP","endereco":{"codigo":"34565","cep":"09015972","logradouro":"AVENIDA ARTUR DE QUEIROS","numero":"961","localidade":"SANTO ANDRE","uf":"SP","bairro":"CASA BRANCA","latitude":"-23","longitude":"-46"}}],"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"15/09/2021"},{"tipo":"RO","status":"01","data":"15/09/2021","hora":"18:02","criacao":"15092021180222","descricao":"Objeto em trânsito - por favor aguarde","unidade":{"local":"AGF DOUTOR JOAO MENDES","codigo":"01501971","cidade":"SAO PAULO","uf":"SP","sto":"00237078","tipounidade":"Agência dos Correios","endereco":{"codigo":"334518","cep":"01501970","logradouro":"PRACA DOUTOR JOAO MENDES","numero":"32","localidade":"SAO PAULO","uf":"SP","bairro":"CENTRO","latitude":"-23.5518795","longitude":"-46.6346488"}},"destino":[{"local":"CTE SAUDE","codigo":"04293970","cidade":"SAO PAULO","bairro":"SAUDE","uf":"SP","endereco":{"codigo":"41919","cep":"04293970","logradouro":"RUA DO BOQUEIRAO","numero":"320","localidade":"SAO PAULO","uf":"SP","bairro":"SAUDE","latitude":"-23.6215902","longitude":"-46.6171872"}}],"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"15/09/2021"},{"tipo":"PO","status":"01","data":"15/09/2021","hora":"16:07","criacao":"15092021160759","descricao":"Objeto postado","unidade":{"local":"AGF DOUTOR JOAO MENDES","codigo":"01501971","cidade":"SAO PAULO","uf":"SP","sto":"00237078","tipounidade":"Agência dos Correios","endereco":{"codigo":"334518","cep":"01501970","logradouro":"PRACA DOUTOR JOAO MENDES","numero":"32","localidade":"SAO PAULO","uf":"SP","bairro":"CENTRO","latitude":"-23.5518795","longitude":"-46.6346488"}},"postagem":{"cepdestino":"44640000","ar":"N","mp":"N","dh":"N","peso":"0","volume":"0","dataprogramada":"01/01/1900 00:00:00","datapostagem":"01/01/1900 00:00:00","prazotratamento":"0"},"cepDestino":"44640000","prazoGuarda":"0","diasUteis":"0","dataPostagem":"15/09/2021"}]},{"numero":"LB300457615HK","sigla":"LB","nome":"OBJETO INTERNACIONAL PRIME","categoria":"PRIME IMPORTAÇÃO","evento":[{"tipo":"BDE","status":"01","data":"15/09/2021","hora":"11:41","criacao":"15092021114132","descricao":"Objeto entregue ao destinatário","recebedor":{"nome":"?","documento":"?","comentario":"?"},"unidade":{"local":"CDD VILA PIRES","codigo":"09110972","cidade":"SANTO ANDRE","uf":"SP","sto":"00025432","tipounidade":"Unidade de Distribuição","endereco":{"codigo":"34565","cep":"09015972","logradouro":"AVENIDA ARTUR DE QUEIROS","numero":"961","localidade":"SANTO ANDRE","uf":"SP","bairro":"CASA BRANCA","latitude":"-23","longitude":"-46"}},"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"23/08/2021"},{"tipo":"OEC","status":"01","data":"15/09/2021","hora":"10:12","criacao":"15092021101226","descricao":"Objeto saiu para entrega ao destinatário","unidade":{"local":"CDD VILA PIRES","codigo":"09110972","cidade":"SANTO ANDRE","uf":"SP","sto":"00025432","tipounidade":"Unidade de Distribuição","endereco":{"codigo":"34565","cep":"09015972","logradouro":"AVENIDA ARTUR DE QUEIROS","numero":"961","localidade":"SANTO ANDRE","uf":"SP","bairro":"CASA BRANCA","latitude":"-23","longitude":"-46"}},"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"23/08/2021","detalheOEC":{"carteiro":"89056400","distrito":"305","lista":"111100017464","unidade":"09110972"}},{"tipo":"FC","status":"05","data":"15/09/2021","hora":"08:20","criacao":"15092021082017","descricao":"Objeto devolvido aos Correios","unidade":{"local":"CDD VILA PIRES","codigo":"09110972","cidade":"SANTO ANDRE","uf":"SP","sto":"00025432","tipounidade":"Unidade de Distribuição","endereco":{"codigo":"34565","cep":"09015972","logradouro":"AVENIDA ARTUR DE QUEIROS","numero":"961","localidade":"SANTO ANDRE","uf":"SP","bairro":"CASA BRANCA","latitude":"-23","longitude":"-46"}},"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"23/08/2021"},{"tipo":"BDR","status":"01","data":"14/09/2021","hora":"19:00","criacao":"14092021190000","descricao":"Objeto entregue ao destinatário","recebedor":{"nome":"                                                                        ","documento":"                         ","comentario":"                         "},"unidade":{"local":"CDD VILA PIRES","codigo":"09110972","cidade":"SANTO ANDRE","uf":"SP","sto":"00025432","tipounidade":"Unidade de Distribuição","endereco":{"codigo":"34565","cep":"09015972","logradouro":"AVENIDA ARTUR DE QUEIROS","numero":"961","localidade":"SANTO ANDRE","uf":"SP","bairro":"CASA BRANCA","latitude":"-23","longitude":"-46"}},"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"23/08/2021"},{"tipo":"OEC","status":"01","data":"14/09/2021","hora":"10:40","criacao":"14092021104033","descricao":"Objeto saiu para entrega ao destinatário","unidade":{"local":"CDD VILA PIRES","codigo":"09110972","cidade":"SANTO ANDRE","uf":"SP","sto":"00025432","tipounidade":"Unidade de Distribuição","endereco":{"codigo":"34565","cep":"09015972","logradouro":"AVENIDA ARTUR DE QUEIROS","numero":"961","localidade":"SANTO ANDRE","uf":"SP","bairro":"CASA BRANCA","latitude":"-23","longitude":"-46"}},"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"23/08/2021","detalheOEC":{"carteiro":"89056400","distrito":"305","lista":"111100017414","unidade":"09110972"}},{"tipo":"DO","status":"01","data":"11/09/2021","hora":"09:49","criacao":"11092021094906","descricao":"Objeto em trânsito - por favor aguarde","unidade":{"local":"CTE SAUDE","codigo":"04293970","cidade":"SAO PAULO","uf":"SP","sto":"00035712","tipounidade":"Unidade de Tratamento","endereco":{"codigo":"41919","cep":"04293970","logradouro":"RUA DO BOQUEIRAO","numero":"320","localidade":"SAO PAULO","uf":"SP","bairro":"SAUDE","latitude":"-23.6215902","longitude":"-46.6171872"}},"destino":[{"local":"CDD VILA PIRES","codigo":"09110972","cidade":"SANTO ANDRE","bairro":"CASA BRANCA","uf":"SP","endereco":{"codigo":"34565","cep":"09015972","logradouro":"AVENIDA ARTUR DE QUEIROS","numero":"961","localidade":"SANTO ANDRE","uf":"SP","bairro":"CASA BRANCA","latitude":"-23","longitude":"-46"}}],"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"23/08/2021"},{"tipo":"RO","status":"01","data":"10/09/2021","hora":"11:33","criacao":"10092021113312","descricao":"Objeto em trânsito - por favor aguarde","unidade":{"local":"UNIDADE INTERNACIONAL CURITIBA","codigo":"80235980","cidade":"CURITIBA","uf":"PR","sto":"00423791","tipounidade":"Unidade Operacional","endereco":{"codigo":"192950","cep":"83330110","logradouro":"UNIDADE INTERNACIONAL PINHAIS","localidade":"CURITIBA","uf":"PR","bairro":"PINHAIS","latitude":"-25","longitude":"-49"}},"destino":[{"local":"CTE SAUDE","codigo":"04293970","cidade":"SAO PAULO","bairro":"SAUDE","uf":"SP","endereco":{"codigo":"41919","cep":"04293970","logradouro":"RUA DO BOQUEIRAO","numero":"320","localidade":"SAO PAULO","uf":"SP","bairro":"SAUDE","latitude":"-23.6215902","longitude":"-46.6171872"}}],"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"23/08/2021"},{"tipo":"PAR","status":"10","data":"10/09/2021","hora":"11:31","criacao":"10092021113112","descricao":"Fiscalização aduaneira finalizada","unidade":{"local":"UNIDADE INTERNACIONAL CURITIBA","codigo":"80235980","cidade":"CURITIBA","uf":"PR","sto":"00423791","tipounidade":"Unidade Operacional","endereco":{"codigo":"192950","cep":"83330110","logradouro":"UNIDADE INTERNACIONAL PINHAIS","localidade":"CURITIBA","uf":"PR","bairro":"PINHAIS","latitude":"-25","longitude":"-49"}},"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"23/08/2021"},{"tipo":"PAR","status":"31","data":"09/09/2021","hora":"04:10","criacao":"09092021041047","descricao":"Pagamento confirmado","unidade":{"local":"CENTRO INTERNACIONAL PR","codigo":"81010971","cidade":"CURITIBA","uf":"PR","sto":"00423791","tipounidade":"Unidade de Logística Integrada","endereco":{"codigo":"296118","cep":"83330110","logradouro":"RUA SALGADO FILHO","numero":"476","localidade":"CURITIBA","uf":"PR","bairro":"CURITIBA","latitude":"-25","longitude":"-49"}},"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"23/08/2021"},{"tipo":"PAR","status":"17","data":"08/09/2021","hora":"18:59","criacao":"08092021185957","descricao":"Aguardando pagamento","unidade":{"local":"CENTRO INTERNACIONAL PR","codigo":"81010971","cidade":"CURITIBA","uf":"PR","sto":"00423791","tipounidade":"Unidade de Logística Integrada","endereco":{"codigo":"296118","cep":"83330110","logradouro":"RUA SALGADO FILHO","numero":"476","localidade":"CURITIBA","uf":"PR","bairro":"CURITIBA","latitude":"-25","longitude":"-49"}},"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"23/08/2021"},{"tipo":"PAR","status":"21","data":"06/09/2021","hora":"16:20","criacao":"06092021162021","descricao":"Encaminhado para fiscalização aduaneira","unidade":{"local":"CENTRO INTERNACIONAL PR","codigo":"81010971","cidade":"CURITIBA","uf":"PR","sto":"00423791","tipounidade":"Unidade de Logística Integrada","endereco":{"codigo":"296118","cep":"83330110","logradouro":"RUA SALGADO FILHO","numero":"476","localidade":"CURITIBA","uf":"PR","bairro":"CURITIBA","latitude":"-25","longitude":"-49"}},"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"23/08/2021"},{"tipo":"PAR","status":"16","data":"27/08/2021","hora":"20:24","criacao":"27082021202437","descricao":"Objeto recebido pelos Correios do Brasil","unidade":{"local":"UNIDADE INTERNACIONAL CURITIBA","codigo":"80235980","cidade":"CURITIBA","uf":"PR","sto":"00423791","tipounidade":"Unidade Operacional","endereco":{"codigo":"192950","cep":"83330110","logradouro":"UNIDADE INTERNACIONAL PINHAIS","localidade":"CURITIBA","uf":"PR","bairro":"PINHAIS","latitude":"-25","longitude":"-49"}},"cepDestino":"09111580","prazoGuarda":"0","diasUteis":"0","dataPostagem":"23/08/2021"}]},{"numero":"LB205714809HK","categoria":"ERRO: Objeto não encontrado na base de dados dos Correios."},{"numero":"NX143141975BR","categoria":"ERRO: Objeto não encontrado na base de dados dos Correios."},{"numero":"LE261562552SE","categoria":"ERRO: Objeto não encontrado na base de dados dos Correios."},{"numero":"OF405104119BR","categoria":"ERRO: Objeto não encontrado na base de dados dos Correios."}]}"""
	val (parcels) = gson.fromJson<TrackingResponse>(response)
	return parcels.mapIndexed { index, parcel -> parcel.copy(name = "Test ${index + 1}") }
}