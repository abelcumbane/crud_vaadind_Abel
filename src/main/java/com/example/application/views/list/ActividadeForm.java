package com.example.application.views.list;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.example.application.data.entity.TipoActividade;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.data.binder.BeanValidationBinder;


public class ActividadeForm extends FormLayout { 
  TextField id = new TextField("id"); 
  TextField descricao = new TextField("descricao");
  TextField avaliacao = new TextField("avaliacao");
  Binder<TipoActividade> binder = new BeanValidationBinder<>(TipoActividade.class);
  
  Button save = new Button("Save");
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");

  public ActividadeForm() {
    addClassName("actividade-form");
    binder.bindInstanceFields(this);

    add(id, 
    	descricao,
        avaliacao,
        createButtonsLayout());
  }
  
  public void setContact(TipoActividade actividade) {
      binder.setBean(actividade); 
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
public static abstract class ContactFormEvent extends ComponentEvent<ActividadeForm> {
 private TipoActividade actividade;

 protected ContactFormEvent(ActividadeForm source,TipoActividade actividade) { 
   super(source, false);
   this.actividade = actividade;
 }

 public TipoActividade getTipoActividade() {
   return actividade;
 }
}

public static class SaveEvent extends ContactFormEvent {
 SaveEvent(ActividadeForm source,TipoActividade actividade) {
   super(source, actividade);
 }
}

public static class DeleteEvent extends ContactFormEvent {
 DeleteEvent(ActividadeForm source,TipoActividade actividade) {
   super(source, actividade);
 }

}

public static class CloseEvent extends ContactFormEvent {
 CloseEvent(ActividadeForm source) {
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
