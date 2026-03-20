package relogiosLogicos;

// Classe que representa um processo no sistema distribuído
class ProcessoLamport {
    private int id;        // Identificador único do processo
    private int relogio;   // Relógio lógico (contador)

    // Construtor: inicializa o processo com ID e relógio zerado
    public ProcessoLamport(int id) {
        this.id = id;
        this.relogio = 0; // Todo processo começa com tempo 0
    }

    // Evento interno (não envolve comunicação)
    public void eventoInterno() {
        relogio++; // Incrementa o relógio a cada evento
        System.out.println("Processo " + id + " evento interno. Relógio: " + relogio);
    }

    // Método para enviar mensagem
    public int enviarMensagem() {
        relogio++; // Incrementa antes de enviar (regra de Lamport)
        System.out.println("Processo " + id + " enviou mensagem. Relógio: " + relogio);
        
        return relogio; // Envia o valor do relógio junto com a mensagem
    }

    // Método para receber mensagem
    public void receberMensagem(int timestampRecebido) {
        // Atualiza o relógio pegando o maior valor entre:
        // - o relógio local
        // - o timestamp recebido
        // e somando 1
        relogio = Math.max(relogio, timestampRecebido) + 1;

        System.out.println("Processo " + id + " recebeu mensagem. Relógio: " + relogio);
    }
}

// Classe principal (onde o programa executa)
public class LamportDemo {
    public static void main(String[] args) {

        // Criando dois processos simulados
        ProcessoLamport p1 = new ProcessoLamport(1);
        ProcessoLamport p2 = new ProcessoLamport(2);

        // Evento interno em P1
        p1.eventoInterno();

        // P1 envia mensagem para P2
        int msg = p1.enviarMensagem();

        // P2 recebe a mensagem enviada por P1
        p2.receberMensagem(msg);

        // Evento interno em P2
        p2.eventoInterno();
    }
}