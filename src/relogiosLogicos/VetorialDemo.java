package relogiosLogicos;

import java.util.Arrays; // Import para imprimir o vetor

// Classe que representa um processo com relógio vetorial
class ProcessoVetorial {
    private int id;         // Identificador do processo
    private int[] relogio;  // Vetor de relógios

    // Construtor
    public ProcessoVetorial(int id, int totalProcessos) {
        this.id = id;
        this.relogio = new int[totalProcessos]; // Inicializa vetor com zeros
    }

    // Evento interno (apenas dentro do processo)
    public void eventoInterno() {
        relogio[id]++; // Incrementa apenas a posição do próprio processo
        System.out.println("P" + id + " evento interno: " + Arrays.toString(relogio));
    }

    // Método para enviar mensagem
    public int[] enviarMensagem() {
        relogio[id]++; // Incrementa antes de enviar
        System.out.println("P" + id + " enviou mensagem: " + Arrays.toString(relogio));

        // Retorna uma cópia do vetor (evita compartilhar memória)
        return relogio.clone();
    }

    // Método para receber mensagem
    public void receberMensagem(int[] recebido) {

        // Atualiza o vetor comparando posição por posição
        for (int i = 0; i < relogio.length; i++) {
            // Para cada posição, pega o maior valor
            relogio[i] = Math.max(relogio[i], recebido[i]);
        }

        relogio[id]++; // Incrementa após receber a mensagem

        System.out.println("P" + id + " recebeu mensagem: " + Arrays.toString(relogio));
    }
}

// Classe principal
public class VetorialDemo {
    public static void main(String[] args) {

        int n = 2; // Número total de processos

        // Criando dois processos (P0 e P1)
        ProcessoVetorial p0 = new ProcessoVetorial(0, n);
        ProcessoVetorial p1 = new ProcessoVetorial(1, n);

        // Evento interno em P0
        p0.eventoInterno();

        // P0 envia mensagem para P1
        int[] msg = p0.enviarMensagem();

        // P1 recebe a mensagem
        p1.receberMensagem(msg);

        // Evento interno em P1
        p1.eventoInterno();
    }
}