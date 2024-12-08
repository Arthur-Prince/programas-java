import { Component } from '@angular/core';
import { ApiserviceService } from './services/apiservice.service';
import { ChartDataSets, ChartOptions } from 'chart.js';
import { elemetsTreinamento } from './services/class-export';
import { Color, Label } from 'ng2-charts';

// node_modules/ng2-charts/lib/base-chart.directive.d.ts

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'ep-ia';
  selected = 'option1'
  step1 = true;
  step2 = true;
  step3 = true;
  mensagem1=''
  mensagem2=''
  mensagem3=''
  mensagem4=''
  mensagem5=''
  matriz2 = true;
  matrizDeConfusao: any
  matrizDeConfusaoCaracter: any
  pesos: any
  xor = false
  or = false
  and = false
  caracteres = false
  pesosMostrar: boolean = false;
  graficoBoolean = false
  temp = [0];
  lineChartData: ChartDataSets[] = [
    { data: [85, 72, 78, 75, 77, 75], label: 'Crude oil prices' },
  ];
  lineChartLabels: Label[] = ['January', 'February', 'March', 'April', 'May', 'June'];
  lineChartOptions = {
    responsive: true,
  };
  lineChartLegend = true;
  lineChartPlugins = [];
  lineChartType = 'line';
  lineChartColors: Color[] = [
    {
      borderColor: 'green',
      backgroundColor: 'rgba(111, 207, 151, 0.3)',
    },
  ];

  dropDownData = ['Pesos aleatórios', 'Pesos perfeitos iniciais', 'Pesos perfeitos finais']

  constructor(private http: ApiserviceService) { }
  ngOnInit(): void {
  }

  MostrarImg(img: string) {
    this.matriz2 = false;
    this.xor = false
    this.or = false
    this.and = false
    this.caracteres = false
    if (img === 'caracteres') {
      this.caracteres = true
    }
    this.http.getMudarProblema(img).subscribe(
      (problema: any) => {
        this.matriz2 = false;
      })
  }

  postMudarProblema(funcao: string) {
    this.pesosMostrar = false;
    this.xor = false;
    this.or = false;
    this.and = false;
    this.caracteres = false;
    
    this.trocarPesos('Pesos aleatórios');
    this.pesos = [''];
    if (funcao === 'XOR') {
      this.xor = true
    }
    if (funcao === 'OR') {
      this.or = true
    }
    if (funcao === 'AND') {
      this.and = true
    }
    this.http.getMudarProblema(funcao).subscribe(
      (problema: any) => {
        this.matriz2 = true;
      })
  }

  treinar() {
    
    var tempLabel: [any]
    this.http.getTreinamento().subscribe(
      (treinamento: elemetsTreinamento) => {
        this.pesosMostrar = true
        this.pesos = treinamento.pesos
        this.temp = treinamento.dadosDoGrafico;
        treinamento.dadosDoGrafico.length;
        this.lineChartData = [
          { data: this.temp, label: 'Acertos' },
        ];
        for (let index = 0; index < this.temp.length; index++) {
          if (index === 0) {
            tempLabel = [(index + 1).toString()];
          } else {
            tempLabel.push((index + 1).toString())
          }
        }
        this.lineChartLabels = tempLabel;
        this.graficoBoolean = true;
        if (treinamento.nome === 'caracteres') {
          this.matrizDeConfusaoCaracter = treinamento.matrizDeConfusao
        } else {
          this.matrizDeConfusao = treinamento.matrizDeConfusao
        }
      }
    )
  }

  treinar1() {
    
    var tempLabel: [any]
    this.http.getTreinamento1().subscribe(
      (treinamento: elemetsTreinamento) => {
        this.pesosMostrar = true
        this.pesos = treinamento.pesos
        this.temp = treinamento.dadosDoGrafico;
        treinamento.dadosDoGrafico.length;
        this.lineChartData = [
          { data: this.temp, label: 'Acertos' },
        ];
        for (let index = 0; index < this.temp.length; index++) {
          if (index === 0) {
            tempLabel = [(index + 1).toString()];
          } else {
            tempLabel.push((index + 1).toString())
          }
        }
        this.lineChartLabels = tempLabel;
        this.graficoBoolean = true;
        if (treinamento.nome === 'caracteres') {
          this.matrizDeConfusaoCaracter = treinamento.matrizDeConfusao
        } else {
          this.matrizDeConfusao = treinamento.matrizDeConfusao
        }
      }
    )
  }
  trocarPesos(kk: any) {
    
    if (kk==='Pesos aleatórios') {
      this.http.getPesosAleatorios().subscribe(
        (pesos) => {
        }
      )
    }
    if (kk==='Pesos perfeitos iniciais') {
      this.http.getPesosPerfeitosIniciais().subscribe(
        (pesos) => {
        }
      )
    }
    if (kk==='Pesos perfeitos finais') {
      this.http.getPesosPerfeitosFinais().subscribe(
        (pesos) => {
        }
      )
    }
  }
  post(palavra: string) {
    this.mensagem1 = ''
    this.http.post(palavra).subscribe(
      (resposta: any) => {
        this.mensagem1 = resposta
      },
      (error: any)=>{
        this.mensagem1 = error.error.text
      }
    )
  }

}
