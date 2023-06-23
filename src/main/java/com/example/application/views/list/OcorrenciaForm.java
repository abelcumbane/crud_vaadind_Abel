
package com.example.application.views.list;


import com.example.application.data.entity.TipoOcorrencia;
import com.example.application.views.list.OcorrenciaForm.CloseEvent;
import com.example.application.views.list.OcorrenciaForm.ContactFormEvent;
import com.example.application.views.list.OcorrenciaForm.DeleteEvent;
import com.example.application.views.list.OcorrenciaForm.SaveEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class OcorrenciaForm  extends FormLayout { 
  TextField id = new TextField("id"); 
  TextField descricao = new TextField("descricao");
  Binder<TipoOcorrencia> binder = new BeanValidationBinder<>(TipoOcorrencia.class);
  
  Button save = new Button("Save");
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");

  public OcorrenciaForm() {
    addClassName("ocorrencia-form");
    binder.bindInstanceFields(this);

    add(id, 
    	descricao,
        createButtonsLayout());
  }
  
  public void setContact(TipoOcorrencia ocorrencia) {
      binder.setBean(ocorrencia); 
  }

  private HorizontalLayout createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY); 
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    save.addClickShortcut(Key.ENTER); 
    close.addClickShortcut(Key.ESCAPE);
    
    save.addClickListener(event -> validateAndSave()); 
    delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean()))); 
    close.addClickListener(event -> fireEvent(new CloseEvent(this))); 

    binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

    return new HorizontalLayout(save, delete, close); 
  }
  
  private void validateAndSave() {
	  if(binder.isValid()) {
	    fireEvent(new SaveEvent(this, binder.getBean())); 
	  }
	}
  
//Events
public static abstract class ContactFormEvent extends ComponentEvent<OcorrenciaForm> {
 private TipoOcorrencia ocorrencia;

 protected ContactFormEvent(OcorrenciaForm source,TipoOcorrencia ocorrencia) { 
   super(source, false);
   this.ocorrencia = ocorrencia;
 }

 public TipoOcorrencia getTipoOcorrencia() {
   return ocorrencia;
 }
}

public static class SaveEvent extends ContactFormEvent {
 SaveEvent(OcorrenciaForm source,TipoOcorrencia ocorrencia) {
   super(source, ocorrencia);
 }
}

public static class DeleteEvent extends ContactFormEvent {
 DeleteEvent(OcorrenciaForm source,TipoOcorrencia ocorrencia) {
   super(source, ocorrencia);
 }

}

public static class CloseEvent extends ContactFormEvent {
 CloseEvent(OcorrenciaForm source) {
   super(source, null);
 }
}

public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) { 
 return addListener(DeleteEvent.class, listener);
}

public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
 return addListener(SaveEvent.class, listener);
}
public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
 return addListener(CloseEvent.class, listener);
}
  
}

