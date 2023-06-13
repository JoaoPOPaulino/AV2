package principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import modulo.*;
import modulo2.*;
public class Main {
        private static List<Usuario> usuarios = new ArrayList<>();
        private static List<Reserva> reservas = new ArrayList<>();
        private static List<Quarto> quartos = new ArrayList<>();

        private static Scanner scan = new Scanner(System.in);

        public static void main(String[] args) {
            inicializarUsuarios();
            inicializarQuartos();
            inicializarReserva();
            exibirMenuPrincipal();

        }

        private static void inicializarUsuarios() {
            Usuario joao = new Usuario("joao", "admin", "admin123", TipoUsuario.ADMINISTRADOR, new Telefone("11", "111111111"));
            Usuario carla = new Usuario("carla", "recep", "recep123", TipoUsuario.RECEPCIONISTA, new Telefone("11", "222222222"));
            Usuario igor = new Usuario("Igor", "igor", "igor123", TipoUsuario.HOSPEDE, new Telefone("66", "444444444"));
            Usuario laura = new Usuario("laura", "laura", "laura123", TipoUsuario.HOSPEDE, new Telefone("88", "999999999"));

            usuarios.add(joao);
            usuarios.add(carla);
            usuarios.add(igor);
            usuarios.add(laura);
        }

        private static void inicializarQuartos() {
            Quarto quarto1 = new Quarto(101, TipoQuarto.LUXO_SIMPLES, 200.0);
            Quarto quarto2 = new Quarto(102, TipoQuarto.LUXO_DUPLO, 300.0);
            Quarto quarto3 = new Quarto(201, TipoQuarto.PRESIDENCIAL, 500.0);
            Quarto quarto4 = new Quarto(202, TipoQuarto.LUXO_SIMPLES, 200.0);

            quartos.add(quarto1);
            quartos.add(quarto2);
            quartos.add(quarto3);
            quartos.add(quarto4);
        }

        private static void inicializarReserva(){
            Reserva reserva1 = new Reserva(LocalDate.of(2023, 06,10), LocalDate.of(2023,06,15), new Quarto(100, TipoQuarto.PRESIDENCIAL, 500.0),
                    new Usuario("testi", "tezte", "teste123", TipoUsuario.HOSPEDE, new Telefone("666", "7777777777")));
            reservas.add(reserva1);
        }


        private static void exibirMenuPrincipal() {
            int opcao;

            do {
                System.out.println("=== Menu Principal ===");
                System.out.println("1. Login");
                System.out.println("2. Cadastro");
                System.out.println("3. Sair");
                System.out.print("Selecione uma opção: ");
                opcao = scan.nextInt();

                switch (opcao) {
                    case 1:
                        autenticarUsuario(scan);
                        break;

                    case 2:
                        cadastrarUsuario(scan);
                        break;
                    case 3:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            } while (opcao != 2);
        }

        private static void autenticarUsuario(Scanner scan) {

            System.out.print("Digite o login: ");
            String login = scan.next();
            System.out.print("Digite a senha: ");
            String senha = scan.next();

            try {
                Usuario usuarioAutenticado = obterUsuarioPeloLogin(login);
                if (usuarioAutenticado.getSenha().equals(senha)) {
                    System.out.println("Usuário autenticado: " + usuarioAutenticado.getNome());

                    if (usuarioAutenticado.getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
                        exibirMenuAdministrador(scan);
                    } else if (usuarioAutenticado.getTipoUsuario() == TipoUsuario.RECEPCIONISTA) {
                        exibirMenuRecepcionista(scan);
                    } else if (usuarioAutenticado.getTipoUsuario() == TipoUsuario.HOSPEDE) {
                        exibirMenuHospede(scan);
                    }
                } else {
                    System.out.println("Credencias inválidas");
                }

            } catch (AutenticacaoException e) {
                System.out.println("Falha na autenticacão: " + e.getMessagem());
            }
        }


        private static Usuario obterUsuarioPeloLogin(String login) throws AutenticacaoException {
            for (Usuario usuario : usuarios) {
                if (usuario.getLogin().equals(login)) {
                    return usuario;
                }
            }

            throw new AutenticacaoException("Usuário não encontrado");
        }

        private static void exibirMenuAdministrador(Scanner scan) {
            int opcao;

            do {
                System.out.println("=== Menu do Administrador ===");
                System.out.println("1. Cadastrar usuário");
                System.out.println("2. Exibir usuários");
                System.out.println("3. Editar usuário");
                System.out.println("4. Excluir usuário");
                System.out.println("5. Sair");
                System.out.print("Selecione uma opção: ");
                opcao = scan.nextInt();
                switch (opcao) {
                    case 1:
                        cadastrarUsuario(scan);
                        break;
                    case 2:
                        exibirUsuarios();
                        break;
                    case 3:
                        editarUsuario(scan);
                        break;
                    case 4:
                        excluirUsuario(scan);
                        break;
                    case 5:
                        System.out.println("Saindo do menu do Administrador...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            } while (opcao != 5);
        }


        private static void cadastrarUsuario(Scanner scan) {
            System.out.println("Digite o nome do usuário: ");
            String nome = scan.next();
            System.out.println("Digite o login do usuário: ");
            String login = scan.next();
            System.out.println("Digite a senha do usuário: ");
            String senha = scan.next();
            System.out.println("Digite seu DDD: ");
            String ddd = scan.next();
            System.out.println("Digite seu número: ");
            String numero = scan.next();
            System.out.println("Selecione o tipo de usuário: ");
            System.out.println("1. Administrador");
            System.out.println("2. Recepcionista");
            System.out.println("3. Hóspede");
            System.out.print("Digite o número correspondente: ");
            int tipoUsuarioOpcao = scan.nextInt();

            TipoUsuario tipoUsuario;

            switch (tipoUsuarioOpcao) {
                case 1:
                    tipoUsuario = TipoUsuario.ADMINISTRADOR;
                    break;
                case 2:
                    tipoUsuario = TipoUsuario.RECEPCIONISTA;
                    break;
                case 3:
                    tipoUsuario = TipoUsuario.HOSPEDE;
                    break;
                default:
                    System.out.println("Opção inválida. Usuário será cadastrado como Hóspede.");
                    tipoUsuario = TipoUsuario.HOSPEDE;
                    break;
            }

            Usuario novoUsuario = new Usuario(nome, login, senha, tipoUsuario, new Telefone(ddd, numero));
            usuarios.add(novoUsuario);

            System.out.println("Usuário cadastrado com sucesso.");
        }

    private static void exibirUsuarios() {
        System.out.println("=== Lista de Usuários ===");

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {

            System.out.println("Opções de impressão:");
            System.out.println("1. Ordenar por nome");
            System.out.println("2. Ordenar por login");
            System.out.println("3. Filtrar por tipo de usuário");
            System.out.println("4. Exibir todos os usuários");
            System.out.println("5. Sair");

            // Leitura da opção selecionada
            int opcao = scan.nextInt();
            switch (opcao) {
                case 1:
                    Collections.sort(usuarios, (u1, u2) -> u1.getNome().compareToIgnoreCase(u2.getNome()));
                    break;
                case 2:
                    Collections.sort(usuarios, Comparator.comparing(Usuario::getLogin));
                    break;
                case 3:
                    System.out.println("Filtrar por tipo de usuário:");
                    System.out.println("1. Administrador");
                    System.out.println("2. Hóspede");
                    System.out.println("3. Recepcionista");

                    int tipoUsuario = scan.nextInt();
                    List<Usuario> usuariosFiltrados = new ArrayList<>();
                    for (Usuario usuario : usuarios) {
                        if (usuario.getTipoUsuario() == TipoUsuario.valueOf(String.valueOf(tipoUsuario))) {
                            usuariosFiltrados.add(usuario);
                        }
                    }
                    usuarios = usuariosFiltrados;

                    break;
                case 4:
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida");
                    break;
            }

            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
    }

        private static void editarUsuario(Scanner scanner) {
            System.out.print("Digite o login do usuário que deseja editar: ");
            String login = scanner.next();

            try {
                Usuario usuarioParaEditar = obterUsuarioPeloLogin(login);

                System.out.print("Digite o novo nome do usuário: ");
                String novoNome = scanner.next();
                System.out.print("Digite a nova senha do usuário: ");
                String novaSenha = scanner.next();

                usuarioParaEditar.setNome(novoNome);
                usuarioParaEditar.setSenha(novaSenha);

                System.out.println("Usuário editado com sucesso.");
            } catch (AutenticacaoException e) {
                System.out.println("Falha na edição do usuário: " + e.getMessage());
            }
        }

        private static void excluirUsuario(Scanner scanner) {
            System.out.print("Digite o login do usuário que deseja excluir: ");
            String login = scanner.next();

            try {
                Usuario usuarioParaExcluir = obterUsuarioPeloLogin(login);
                usuarios.remove(usuarioParaExcluir);
                System.out.println("Usuário excluído com sucesso.");
            } catch (AutenticacaoException e) {
                System.out.println("Falha na exclusão do usuário: " + e.getMessage());
            }
        }

        private static void exibirMenuRecepcionista(Scanner scan) {
            int opcao;

            do {
                System.out.println("=== Menu do Recepcionista ===");
                System.out.println("1. Cadastrar reserva");
                System.out.println("2. Exibir reservas");
                System.out.println("3. Editar reserva");
                System.out.println("4. Excluir reserva");
                System.out.println("5. Sair");
                System.out.print("Selecione uma opção: ");
                opcao = scan.nextInt();

                switch (opcao) {
                    case 1:
                        cadastrarReserva(scan);
                        break;
                    case 2:
                        exibirReservas();
                        break;
                    case 3:
                        editarReserva(scan);
                        break;
                    case 4:
                        excluirReserva(scan);
                        break;
                    case 5:
                        System.out.println("Saindo do menu do Recepcionista...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            } while (opcao != 5);
        }

        private static void cadastrarReserva(Scanner scan) {
            System.out.println("=== Cadastro de Reserva ===");
            System.out.print("Digite o login do hóspede: ");
            String login = scan.next();

            try {
                Usuario hospede = obterUsuarioPeloLogin(login);

                System.out.println("=== Quartos Disponíveis ===");
                exibirQuartosDisponiveis();

                System.out.print("Digite o número do quarto desejado: ");
                int numeroQuarto = scan.nextInt();

                Quarto quartoSelecionado = obterQuartoPeloNumero(numeroQuarto);

                if (quartoSelecionado == null) {
                    System.out.println("Quarto não encontrado.");
                    return;
                }

                System.out.print("Digite a data de check-in (formato: dd/mm/aaaa): ");
                String dataInicioStr = scan.next();
                LocalDate dataInicio = LocalDate.parse(dataInicioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                System.out.print("Digite a data de check-out (formato: dd/mm/aaaa): ");
                String dataFinalStr = scan.next();
                LocalDate dataFinal = LocalDate.parse(dataFinalStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));


                Reserva novaReserva = new Reserva(dataInicio, dataFinal, quartoSelecionado, hospede);
                novaReserva.calcularValorTotal();
                reservas.add(novaReserva);

                System.out.println("Reserva cadastrada com sucesso.");
            } catch (AutenticacaoException e) {
                System.out.println("Falha ao cadastrar reserva: " + e.getMessage());
            }
        }

        private static void exibirQuartosDisponiveis() {
            for (Quarto quarto : quartos) {
                if (!quarto.isReservado()) {
                    System.out.println(quarto);
                }
            }
        }

        private static Quarto obterQuartoPeloNumero(int numeroQuarto) {
            for (Quarto quarto : quartos) {
                if (quarto.getNumero() == numeroQuarto) {
                    return quarto;
                }
            }

            throw new IllegalArgumentException("Quarto não encontrado");
        }


        private static void exibirReservas() {
            System.out.println("=== Lista de Reservas ===");

            if (reservas.isEmpty()) {
                System.out.println("Nenhuma reserva cadastrada.");
            } else {
                for (Reserva reserva : reservas) {
                    System.out.println(reserva);
                }
            }
        }

        private static void editarReserva(Scanner scan) {
            System.out.print("Digite o login do usuario da reserva que dejesa editar: ");
            String login = scan.next();

            try {
                Reserva reservaParaEditar = obterReservaPeloLogin(login);

                System.out.print("Digite a data de início da reserva (formato: dd/mm/aaaa): ");
                String dataInicioStr = scan.next();
                System.out.print("Digite a data de término da reserva (formato: dd/mm/aaaa): ");
                String dataFinalStr = scan.next();

                LocalDate dataInicio = LocalDate.parse(dataInicioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalDate dataFinal = LocalDate.parse(dataFinalStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                reservaParaEditar.setDataInicio(dataInicio);
                reservaParaEditar.setDataFinal(dataFinal);
                reservaParaEditar.calcularValorTotal();

                System.out.println("Reserva editada com sucesso.");
            } catch (IllegalArgumentException e) {
                System.out.println("Falha na edição da reserva: " + e.getMessage());
            }
        }


        private static Reserva obterReservaPeloLogin(String login) {
            for (Reserva reserva : reservas) {
                if (reserva.getUsuario().getLogin().equals(login)) {
                    return reserva;
                }
            }

            throw new IllegalArgumentException("Reserva não encontrada");
        }

        private static void excluirReserva(Scanner scan) {
            System.out.print("Digite o login do usuário da reserva que deseja excluir: ");
            String login = scan.next();

            try {
                Reserva reservaParaExcluir = obterReservaPeloLogin(login);
                reservas.remove(reservaParaExcluir);

                System.out.println("Reserva excluída com sucesso.");
            } catch (IllegalArgumentException e) {
                System.out.println("Falha na exclusão da reserva: " + e.getMessage());
            }
        }

        private static void exibirMenuHospede(Scanner scan) {
            int opcao;

            do {
                System.out.println("=== Menu do Hóspede ===");
                System.out.println("1. Exibir meus dados");
                System.out.println("2. Editar meus dados");
                System.out.println("3. Realizar reserva");
                System.out.println("4. Exibir reservas");
                System.out.println("5. Sair");
                System.out.print("Selecione uma opção: ");
                opcao = scan.nextInt();

                switch (opcao) {
                    case 1:
                        exibirDadosHospede(scan);
                        break;
                    case 2:
                        editarDadosHospede(scan);
                        break;
                    case 3:
                        realizarReserva(scan);
                        break;
                    case 4:
                        exibirReservasHospede(scan);
                        break;
                    case 5:
                        System.out.println("Saindo do menu do Hóspede...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            } while (opcao != 5);
        }
        private static void exibirDadosHospede(Scanner scan) {
            System.out.print("Digite o login do hóspede: ");
            String login = scan.next();

            try {
                Usuario hospede = obterUsuarioPeloLogin(login);

                if (hospede.getTipoUsuario() == TipoUsuario.HOSPEDE) {
                    System.out.println("=== Dados do Hóspede ===");
                    System.out.println("Nome: " + hospede.getNome());
                    System.out.println("Login: " + hospede.getLogin());
                    System.out.println("Senha: " + hospede.getSenha());
                } else {
                    System.out.println("Login não corresponde a um hóspede.");
                }
            } catch (AutenticacaoException e) {
                System.out.println("Falha ao obter os dados do hóspede: " + e.getMessage());
            }
        }

    private static void editarDadosHospede(Scanner scan) {
        System.out.print("Digite o login do hóspede: ");
        String login = scan.next();

        try {
            Usuario hospede = obterUsuarioPeloLogin(login);

            if (hospede.getTipoUsuario() == TipoUsuario.HOSPEDE) {
                System.out.println("=== Editar Dados do Hóspede ===");
                System.out.println("1. Editar nome");
                System.out.println("2. Editar login");
                System.out.println("3. Editar senha");
                System.out.println("4. Editar telefone");
                System.out.println("5. Voltar");

                System.out.print("Selecione uma opção: ");
                int opcao = scan.nextInt();

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o novo nome: ");
                        String novoNome = scan.next();
                        hospede.setNome(novoNome);
                        System.out.println("Nome editado com sucesso.");
                        break;
                    case 2:
                        System.out.print("Digite o novo login: ");
                        String novoLogin = scan.next();
                        hospede.setLogin(novoLogin);
                        System.out.println("Login editado com sucesso.");
                        break;
                    case 3:
                        System.out.print("Digite a nova senha: ");
                        String novaSenha = scan.next();
                        hospede.setSenha(novaSenha);
                        System.out.println("Senha editada com sucesso.");
                        break;
                    case 4:
                        System.out.print("Digite o novo ddd: ");
                        String novoDDD = scan.next();
                        System.out.print("Digite o novo número: ");
                        String novoNumero = scan.next();
                        hospede.getTelefone().setDdd(novoDDD);
                        hospede.getTelefone().setNumero(novoNumero);
                        System.out.println("Telefone editado com sucesso.");
                        break;
                    case 5:
                        System.out.println("Voltando ao menu do Hóspede...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            } else {
                System.out.println("Login não corresponde a um hóspede.");
            }
        } catch (AutenticacaoException e) {
            System.out.println("Falha ao editar os dados do hóspede: " + e.getMessage());
        }
    }

    private static void realizarReserva(Scanner scan) {
        System.out.print("Digite o login do hóspede: ");
        String login = scan.next();

        try {
            Usuario hospede = obterUsuarioPeloLogin(login);

            if (hospede.getTipoUsuario() == TipoUsuario.HOSPEDE) {
                System.out.println("=== Realizar Reserva ===");

                System.out.println("Selecione o quarto desejado:");
                exibirQuartosDisponiveis();
                System.out.print("Digite o número do quarto: ");
                int numeroQuarto = scan.nextInt();

                try {
                    Quarto quartoSelecionado = obterQuartoPeloNumero(numeroQuarto);
                    System.out.print("Digite a data de início da reserva (formato: dd/mm/aaaa): ");
                    String dataInicioStr = scan.next();
                    System.out.print("Digite a data de término da reserva (formato: dd/mm/aaaa): ");
                    String dataFinalStr = scan.next();

                    LocalDate dataInicio = LocalDate.parse(dataInicioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    LocalDate dataFinal = LocalDate.parse(dataFinalStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    Reserva novaReserva = new Reserva(dataInicio, dataFinal, quartoSelecionado, hospede);
                    novaReserva.calcularValorTotal();
                    reservas.add(novaReserva);

                    System.out.println("Reserva realizada com sucesso.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Falha ao realizar a reserva: " + e.getMessage());
                }
            } else {
                System.out.println("Login não corresponde a um hóspede.");
            }
        } catch (AutenticacaoException e) {
            System.out.println("Falha ao realizar a reserva: " + e.getMessage());
        }
    }
    private static void exibirReservasHospede(Scanner scan) {
        System.out.print("Digite o login do hóspede: ");
        String login = scan.next();

        try {
            Usuario hospede = obterUsuarioPeloLogin(login);

            if (hospede.getTipoUsuario() == TipoUsuario.HOSPEDE) {
                System.out.println("=== Reservas do Hóspede ===");
                List<Reserva> reservasHospede = obterReservasPorLogin(login);

                if (reservasHospede.isEmpty()) {
                    System.out.println("Nenhuma reserva encontrada.");
                } else {
                    for (Reserva reserva : reservasHospede) {
                        System.out.println(reserva);
                    }
                }
            } else {
                System.out.println("Login não corresponde a um hóspede.");
            }
        } catch (AutenticacaoException e) {
            System.out.println("Falha ao exibir as reservas do hóspede: " + e.getMessage());
        }
    }

    private static List<Reserva> obterReservasPorLogin(String login) {
        List<Reserva> reservasHospede = new ArrayList<>();

        for (Reserva reserva : reservas) {
            if (reserva.getUsuario().getLogin().equals(login)) {
                reservasHospede.add(reserva);
            }
        }

        return reservasHospede;
    }


}