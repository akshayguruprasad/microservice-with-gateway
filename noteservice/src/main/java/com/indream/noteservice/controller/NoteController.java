package com.indream.noteservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.indream.configuration.I18NSpec;
import com.indream.noteservice.model.NoteDto;
import com.indream.noteservice.model.NoteEntity;
import com.indream.noteservice.model.NoteResponse;
import com.indream.noteservice.model.ReminderDto;
import com.indream.noteservice.service.NoteService;
import com.indream.util.Utility;

/**
 * NOTE CONTROLLER FOR THE NOTE REQUESTS
 * 
 * @author Akshay
 *
 */
@RestController
@RequestMapping(path = "/noteapplication")
public class NoteController {
	@Autowired
	NoteService noteService;

	@Autowired
	I18NSpec i18N;

	/*
	 * @purpose REQUEST MAPPING FOR THE CREATE NOTE
	 *
	 * @author akshay
	 * 
	 * @com.indream.fundoo.noteservice.controller
	 * 
	 * @since Jul 24, 2018
	 *
	 */
	@RequestMapping(path = "/createnote", method = RequestMethod.POST)
	public ResponseEntity<NoteResponse> createNote(@RequestBody NoteDto noteEntityDTO, HttpServletRequest request) {
		String noteId = null;
		try {
			String token = (String) request.getHeader("userId");

			noteId = noteService.createNote(noteEntityDTO, token, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<NoteResponse>(
				new NoteResponse(i18N.getMessage("note.create.success") + " with id : " + noteId, 11), HttpStatus.OK);
	}

	/*
	 * @purpose REQUEST MAPPING FOR THE UPDATE NOTE
	 *
	 * @author akshay
	 * 
	 * @com.indream.fundoo.noteservice.controller
	 * 
	 * @since Jul 24, 2018
	 *
	 */
	@RequestMapping(path = "/updatenote", method = RequestMethod.PUT)
	public ResponseEntity<NoteResponse> updateNote(@RequestBody NoteDto noteEntityDTO, HttpServletRequest request) {
		String token = (String) request.getHeader("userId");
		noteService.updateNote(noteEntityDTO, token);
		return new ResponseEntity<NoteResponse>(new NoteResponse(i18N.getMessage("note.update.success"), 11),
				HttpStatus.OK);

	}

	/*
	 * @purpose REQUEST MAPPING FOR THE DELETE NOTE
	 *
	 * @author akshay
	 * 
	 * @com.indream.fundoo.noteservice.controller
	 * 
	 * @since Jul 24, 2018
	 *
	 */
	@RequestMapping(path = "/deletenote/{id:.*}", method = RequestMethod.DELETE)
	public ResponseEntity<NoteResponse> deleteNote(@PathVariable("id") String noteId, HttpServletRequest request) {
		try {
			System.out.println("Hits the delete controller");
			String token = (String) request.getHeader("userId");
			System.out.println(token);
			noteService.deleteNote(noteId, token);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return new ResponseEntity<NoteResponse>(new NoteResponse(i18N.getMessage("note.delete.success"), 11),
				HttpStatus.OK);
	}

	/*
	 * @purpose REQUEST MAPPING FOR THE LIST NOTE
	 *
	 * @author akshay
	 * 
	 * @com.indream.fundoo.noteservice.controller
	 * 
	 * @since Jul 24, 2018
	 *
	 */
	@RequestMapping(path = "/listnote", method = RequestMethod.GET)
	public ResponseEntity<NoteResponse> listNotes(HttpServletRequest request) {
		String token = (String) request.getHeader("userId");
		List<NoteEntity> notes = noteService.selectNote(token);
		String listNotes = notes.stream().map(p -> Utility.covertToJSONString(p))
				.collect(Collectors.joining(",", "[", "]"));
		System.out.println(listNotes);
		return new ResponseEntity<NoteResponse>(new NoteResponse(listNotes, 11), HttpStatus.OK);
	}

	/*
	 * @purpose REQUEST MAPPING FOR THE PIN NOTE
	 *
	 * @author akshay
	 * 
	 * @com.indream.fundoo.noteservice.controller
	 * 
	 * @since Jul 24, 2018
	 *
	 */
	@RequestMapping(path = "/pinnote/{noteId}", method = RequestMethod.PUT)
	public ResponseEntity<String> pinNote(@PathVariable("noteId") String noteId, HttpServletRequest request) {
		String token = (String) request.getHeader("userId");
		noteService.pinNote(noteId, token);
		return new ResponseEntity<String>(i18N.getMessage("note.pin.success"), HttpStatus.OK);
	}

	/*
	 * @purpose REQUEST MAPPING FOR THE TRASH NOTE
	 *
	 * @author akshay
	 * 
	 * @com.indream.fundoo.noteservice.controller
	 * 
	 * @since Jul 24, 2018
	 *
	 */
	@RequestMapping(path = "/trashnote/{noteId}", method = RequestMethod.PUT)
	public ResponseEntity<String> trashNote(@PathVariable("noteId") String noteId, HttpServletRequest request) {
		String token = (String) request.getHeader("userId");
		noteService.deleteNoteToTrash(noteId, token);
		return new ResponseEntity<String>(i18N.getMessage("note.trash.success"), HttpStatus.OK);
	}

	/*
	 * @purpose REQUEST MAPPING FOR THE ARCHIVE NOTE
	 *
	 * @author akshay
	 * 
	 * @com.indream.fundoo.noteservice.controller
	 * 
	 * @since Jul 24, 2018
	 *
	 */
	@RequestMapping(path = "/archivenote/{noteId}", method = RequestMethod.PUT)
	public ResponseEntity<String> archiveNote(@PathVariable("noteId") String noteId, HttpServletRequest request) {
		String token = (String) request.getHeader("userId");
		noteService.archiveNote(noteId, token);
		return new ResponseEntity<String>(i18N.getMessage("note.archive.success"), HttpStatus.OK);
	}

	@RequestMapping(path = "/restorenote/{noteId}", method = RequestMethod.PUT)
	public ResponseEntity<String> restoreNote(@PathVariable("noteId") String noteId, HttpServletRequest request) {
		String token = (String) request.getHeader("userId");
		noteService.restoreNote(noteId, token);
		return new ResponseEntity<String>(i18N.getMessage("note.restore.success"), HttpStatus.OK);
	}

	@RequestMapping(path = "/remindernote", method = RequestMethod.PUT)
	public ResponseEntity<String> reminderNote(@RequestBody ReminderDto noteDto, HttpServletRequest request) {
		String token = (String) request.getHeader("userId");
		System.out.println(noteDto);
		noteService.reminderNote(noteDto, token);
		return new ResponseEntity<String>(i18N.getMessage("note.reminder.success"), HttpStatus.OK);
	}

	@RequestMapping(path = "/createlabel/{label}", method = RequestMethod.POST)
	public ResponseEntity<String> createLabel(@PathVariable("label") String label, HttpServletRequest request) {
		String token = (String) request.getHeader("userId");
		System.out.println("Sression object token is " + token);
		noteService.createLabel(label, token);
		return new ResponseEntity<String>(i18N.getMessage("label.create.success"), HttpStatus.OK);
	}

	@RequestMapping(path = "/editlabel/{label}/{labelId}", method = RequestMethod.PUT)
	public ResponseEntity<String> editLabel(@PathVariable("label") String label, HttpServletRequest request,@PathVariable("labelId") String labelId) {
		String token = (String) request.getHeader("userId");
		noteService.editLabelName(token, label, labelId);
		return new ResponseEntity<String>(i18N.getMessage("label.update.success"), HttpStatus.OK);
	}

	@RequestMapping(path = "/deletelabel/{label}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteLabel(@PathVariable("label") String label, HttpServletRequest request) {
		String token = (String) request.getHeader("userId");
		noteService.deleteLabel(label, token);
		return new ResponseEntity<String>(i18N.getMessage("label.delete.success"), HttpStatus.OK);
	}

	@RequestMapping(path = "/labelnote/{label}/{noteId}", method = RequestMethod.PUT)
	public ResponseEntity<String> labelNote(@PathVariable("label") String label,@PathVariable("noteId") String noteId, HttpServletRequest request) {
		String token = (String) request.getHeader("userId");
		System.out.println(noteId+""+label);
		noteService.setLabelNote(noteId, token, label);
		return new ResponseEntity<String>(i18N.getMessage("note.label.success"), HttpStatus.OK);
	}

	@RequestMapping(path = "/getTrashnote", method = RequestMethod.GET)
	public ResponseEntity<String> getTrashNotes(HttpServletRequest request) {
		String token = (String) request.getHeader("userId");
		List<NoteEntity> entities = noteService.selectTrashNote(token);
		return new ResponseEntity<String>(entities.toString(), HttpStatus.OK);
	}

	@RequestMapping(path = "/getArchivednote", method = RequestMethod.GET)
	public ResponseEntity<String> getArchivedNote(HttpServletRequest request) {
		String token = (String) request.getHeader("userId");
		List<NoteEntity> entities = noteService.selectArchiveNote(token);
		return new ResponseEntity<String>(entities.toString(), HttpStatus.OK);
	}

	@RequestMapping(path = "/getPinnednote", method = RequestMethod.GET)
	public ResponseEntity<String> getPinnedNote(HttpServletRequest request) {
		String token = (String) request.getHeader("userId");
		List<NoteEntity> entities = noteService.selectPinNote(token);
		return new ResponseEntity<String>(entities.toString(), HttpStatus.OK);
	}

	@RequestMapping(path = "/unpinnote", method = RequestMethod.PUT)
	public ResponseEntity<String> unPinNote(String noteId, HttpServletRequest request) {
		String token = (String) request.getHeader("userId");
		noteService.unPinNote(noteId, token);
		return new ResponseEntity<String>(i18N.getMessage("note.unpin.success"), HttpStatus.OK);
	}

	@RequestMapping(path = "/unarchivenote", method = RequestMethod.PUT)
	public ResponseEntity<String> unArchiveNote(String noteId, HttpServletRequest request) {
		String token = (String) request.getHeader("userId");
		noteService.unArchiveNote(noteId, token);
		return new ResponseEntity<String>(i18N.getMessage("note.unarchive.success"), HttpStatus.OK);
	}

	@RequestMapping(path = "/addUrl", method = RequestMethod.PUT)
	public ResponseEntity<String> updateUrlNote(String noteId, String url, HttpServletRequest request) {
		String token = (String) request.getHeader("userId");
		noteService.urlNote(url, noteId, token);
		return new ResponseEntity<String>(i18N.getMessage("note.url.add.success"), HttpStatus.OK);
	}

	@GetMapping("/demo")
	public String demo() {

		return "Hello world";

	}

}
