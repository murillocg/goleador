import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';

import { Jogador } from '../jogadores/shared/jogador.model';
import { JogadorService } from '../jogadores/shared/jogador.service';

@Component({
  selector: 'gol-jogadorForm',
  templateUrl: 'jogador-form.component.html',
  styleUrls: ['jogador-form.component.css']
})
export class JogadorFormComponent implements OnInit {

  private editMode: boolean;
  private jogador: Jogador;

  jogadorForm: FormGroup;
  title: string;
  serverError: boolean;
  errorMessage: string;

  constructor(
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private jogadorService: JogadorService,
    private router: Router) {}

  onSubmit(): void {
    this.updateLocalJogador();
    this.jogadorService.saveJogador(this.jogador).subscribe(
      jogadores => {
        console.log(jogadores);
        this.jogadorForm.reset();
        this.router.navigate(['/jogadores']);
      },
      error => {
        this.serverError = true;
        this.errorMessage = error;
      }
    );
  }

  private updateLocalJogador(): void {
      this.jogador.nome = this.jogadorForm.value.nome;
  }

  ngOnInit(): void {
    this.serverError = false;
    this.errorMessage = '';
    this.jogador = new Jogador();
    this.editMode = false;
    this.title = 'Inclusão de jogador';
    let id: number;
    this.activatedRoute.params.forEach((params: Params) => {
      id = +params['id'];
    });
    if (id) {
      this.editMode = true;
      this.title = 'Alteração de jogador';
      this.activatedRoute.data.subscribe(
        (data: { jogador: Jogador }) => {
          this.jogador = data.jogador;
        }
      );
    }

    this.jogadorForm = this.formBuilder.group({
      nome: [this.jogador.nome, Validators.required]
    });
  }
}
