import org.w3c.dom.Element;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class TarjetaHabitacionController {
    @FXML private VBox tarjeta;
    @FXML private Label lblNumero;
    @FXML private Label lblTipo;
    @FXML private Label lblCapacidad;
    @FXML private Label lblPrecio;
    @FXML private Label lblEstado;

    private Runnable accionDetalles;

    // Recibimos los datos que la pantalla quiere mostrar en esta tarjeta.
    public void mostrarDatos(Element habitacion) {
        lblNumero.setText(habitacion.getAttribute("numero"));
        lblTipo.setText(texto(habitacion, "tipo"));
        lblCapacidad.setText("Capacidad: " + texto(habitacion, "capacidad") + " personas");
        lblPrecio.setText(texto(habitacion, "precioNoche") + " € / noche");
        String estado = habitacion.getAttribute("estado");
        if (estado.equals("libre")) {
            estado = "disponible";
        }
        lblEstado.setText(estado);
    }

    // Permitimos que cada pantalla elija qué hacer al pulsar el botón.
    public void setAccionDetalles(Runnable accion) {
        accionDetalles = accion;
    }

    // Personalizamos la tarjeta sin copiar su diseño.
    public void setDestacada(boolean destacada) {
        tarjeta.getStyleClass().remove("tarjeta-destacada");
        if (destacada) {
            tarjeta.getStyleClass().add("tarjeta-destacada");
        }
    }

    @FXML
    private void verDetalles() {
        if (accionDetalles != null) {
            accionDetalles.run();
        }
    }

    private String texto(Element habitacion, String etiqueta) {
        return habitacion.getElementsByTagName(etiqueta).item(0).getTextContent();
    }
}
